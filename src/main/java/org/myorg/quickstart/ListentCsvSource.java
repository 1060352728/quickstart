package org.myorg.quickstart;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

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
public class ListentCsvSource extends RichSourceFunction<ListentCsvEntity> {
    boolean flag = false;
    @Override
    public void run(SourceContext<ListentCsvEntity> sourceContext) throws Exception {
        int count = 1;
        ListentCsvEntity listentCsvEntity1 = new ListentCsvEntity();
        listentCsvEntity1.setTaskId("12");
        //读文件到Stringbuffer
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("E:\\datafile\\test.csv"));
            /*String str;
            while((str = br.readLine()) != null) {//逐行读取
                sb.append(str);//加在StringBuffer尾
                sb.append("\r\n");//行尾 加换行符
            }*/
            br.close();//别忘记，切记
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        listentCsvEntity1.setCount(sb.toString());
        sourceContext.collect(listentCsvEntity1);
        do{
            ListentCsvEntity listentCsvEntity2 = new ListentCsvEntity();
            listentCsvEntity2.setTaskId(String.valueOf(count));
            listentCsvEntity2.setCount(new Long(1l).toString());
            sourceContext.collect(listentCsvEntity2);
            count ++;
            Thread.sleep(5000);
        }while (!flag);

    }
    /**
     * 递归扫描指定文件夹下面的指定文件
     */
    public ArrayList<String> scanFilesWithRecursion(String folderPath) throws FileNotFoundException {
        ArrayList<String> scanFiles = new ArrayList<>();
        File directory = new File(folderPath);
        if(!directory.isDirectory()){
            throw new FileNotFoundException("folderPath:" + folderPath + "inexistence");
        }
        if(directory.isDirectory()){
            File [] fileList = directory.listFiles();
            if(null != fileList) {
                for (File aFileList : fileList) {
                    if(aFileList.getName().endsWith(".end")){
                        flag = true;
                    }
                    /*如果当前是文件夹，进入递归扫描文件夹**/
                    if (aFileList.isDirectory()) {
                        /*递归扫描下面的文件夹*/
                        scanFilesWithRecursion(aFileList.getAbsolutePath());
                    } else {
                        /*非文件夹*/
                        String filePath = aFileList.getAbsolutePath().replaceAll("\\\\", "//");
                        if(filePath.contains(".csv")){
                            scanFiles.add(filePath);
                        }
                    }
                }
            }
        }
        if(flag){
            return scanFiles;
        }
        return scanFiles;
    }

    @Override
    public void cancel() {

    }
}
