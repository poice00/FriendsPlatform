package com.fp.util;

import java.io.IOException;  
import java.text.SimpleDateFormat;

import KmeansCluster.KmeansCluster;  
  
/**聚类器主类，提供主函数入口 
 * 
 */  
public class ClusterMain {  
  
    /** 
     * @param args 
     * @throws IOException  
     */  
    public static void main(String[] args) throws Exception {  
        // TODO Auto-generated method stub  
        /*DataPreProcess DataPP = new DataPreProcess(); 
        DataPP.BPPMain(args);//数据预处理,注意如果已经完成数据预处理，此函数可以不执行  */        
    	//WordSplit wordSplit=new WordSplit();
        //KmeansSVDCluster kmeansCluster1 = new KmeansSVDCluster();  
        KmeansCluster kmeansCluster = new KmeansCluster();          
        //下面创建聚类算法的测试样例集合  
        /*String srcDir = "F:/DataMiningSample/processedSample_includeNotSpecial/";  
        String destDir = "F:/DataMiningSample/clusterTestSample/";  */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
        String beginTime = sdf.format(new java.util.Date());    
        System.out.println("程序开始执行时间:"+beginTime);    
       /* String[] terms = wordSplit.createTestSamples(srcDir, destDir);  */
        //kmeansCluster1.KmeansClusterMain(destDir, terms);  
        kmeansCluster.KmeansClusterMain("");  
        String endTime = sdf.format(new java.util.Date());    
        System.out.println("程序结束执行时间:"+endTime);    
    }  
}  
