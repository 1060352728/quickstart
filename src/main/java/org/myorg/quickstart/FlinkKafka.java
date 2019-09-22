package org.myorg.quickstart;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;

import java.util.Properties;

/**
 * @Auther: likui
 * @Date: 2019/9/22 18:32
 * @Description:
 */
public class FlinkKafka {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty("zookeeper.connect", "192.168.138.128:2181,192.168.138.129:2181,192.168.138.130:2181,192.168.138.131:2181,192.168.138.132:2181");
        properties.setProperty("bootstrap.servers", "192.168.138.128:9092,192.168.138.129:9092,192.168.138.130:9092,192.168.138.131:9092,192.168.138.132:9092");
        properties.setProperty("group.id","metric-group");
        DataStream stream = environment.addSource(new FlinkKafkaConsumer08<>(
                "tmf", new SimpleStringSchema(), properties) );
        stream.map(new MapFunction<String,String>() {
            private static final long serialVersionUID = -6867736771747690202L;
            @Override
            public String map(String value) throws Exception {
                return "Stream Value: " + value;
            }

        }).print();
        environment.execute();
    }
}
