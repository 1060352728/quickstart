package org.myorg.quickstart;

import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @Auther: likui
 * @Date: 2019/9/19 22:23
 * @Description:
 */
public class ListentCsvSource implements ParallelSourceFunction<ListentCsvEntity> {
    boolean flag = false;
    @Override
    public void run(SourceContext<ListentCsvEntity> sourceContext) throws Exception {
        int count = 1;
        //ListentCsvEntity listentCsvEntity1 = new ListentCsvEntity();
        //listentCsvEntity1.setTaskId("12");
        //读文件到Stringbuffer
        //StringBuffer sb = new StringBuffer();
        //BufferedReader br = null;
        try {
            //br = new BufferedReader(new FileReader("E:\\datafile\\test.csv"));
            /*String str;
            while((str = br.readLine()) != null) {//逐行读取
                sb.append(str);//加在StringBuffer尾
                sb.append("\r\n");//行尾 加换行符
            }*/
            //br.close();//别忘记，切记
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        //listentCsvEntity1.setCount(sb.toString());
        //sourceContext.collect(listentCsvEntity1);
        for (int i = 0; i < 50; i++) {
            ListentCsvEntity listentCsvEntity2 = new ListentCsvEntity();
            listentCsvEntity2.setTaskId(String.valueOf(count));
            listentCsvEntity2.setCount(new Long(1l).toString());
            sourceContext.collect(listentCsvEntity2);
            count ++;
            Thread.sleep(2000);
        }

    }

    @Override
    public void cancel() {

    }
}
