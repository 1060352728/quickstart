package org.myorg.quickstart;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource;

/**
 * @Auther: likui
 * @Date: 2019/9/19 22:17
 * @Description:
 */
public class CsvBash {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();
        see.setParallelism(4);
        DataStream<ListentCsvEntity> edits = see.addSource(new ListentCsvSource()).setParallelism(1);

        KeyedStream<ListentCsvEntity, String> keyedEdits = edits
                .keyBy(new KeySelector<ListentCsvEntity, String>() {
                    @Override
                    public String getKey(ListentCsvEntity event) {
                        return event.getTaskId();
                    }
                });

        DataStream<Tuple2<String, Long>> result = keyedEdits
                .timeWindow(Time.seconds(1))
                .fold(new Tuple2<>("", 0L), new FoldFunction<ListentCsvEntity, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> fold(Tuple2<String, Long> acc, ListentCsvEntity event) {
                        acc.f0 = event.getCount();
                        acc.f1 += Long.valueOf(event.getTaskId());
                        return acc;
                    }
                });
        result.print();
        see.execute();
    }
}
