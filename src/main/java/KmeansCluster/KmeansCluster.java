package KmeansCluster;

import java.io.FileWriter;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
import java.util.TreeMap;  
import java.util.Vector;

import com.fp.util.WordSplit;  
  
/**Kmeans聚类算法的实现类，将newsgroups文档集聚成10类、20类、30类 
 * 算法结束条件:当每个点最近的聚类中心点就是它所属的聚类中心点时，算法结束 
 * 
 */  
  
public class KmeansCluster {  
      
    /**Kmeans算法主过程 
     * @param Map<String, Map<String, Double>> allTestSampleMap 聚类算法测试样本map 
     * @param int K 聚类的数量 
     * @return Map<String,Integer> 聚类的结果  即<文件名，聚类完成后所属的类别标号> 
     * @throws IOException  
     */  
    private Map<String, Integer> doProcess(  
            Map<String, Map<String, Double>> allTestSampleMap, int K) {  
        // TODO Auto-generated method stub  
        //0、首先获取allTestSampleMap所有文件名顺序组成的数组  
        String[] testSampleNames = new String[allTestSampleMap.size()];  
        int count = 0, tsLength = allTestSampleMap.size();  
        Set<Map.Entry<String, Map<String, Double>>> allTestSampeleMapSet = allTestSampleMap.entrySet();  
        for(Iterator<Map.Entry<String, Map<String, Double>>> it = allTestSampeleMapSet.iterator(); it.hasNext(); ){  
            Map.Entry<String, Map<String, Double>> me = it.next();  
            testSampleNames[count++] = me.getKey();  
        }  
        //1、初始点的选择算法是随机选择或者是均匀分开选择，这里采用后者  
        Map<Integer, Map<String, Double>> meansMap = getInitPoint(allTestSampleMap, K);//保存K个中心点  
        double [][] distance = new double[tsLength][K];//distance[i][j]记录点i到聚类中心j的距离  
        //2、初始化K个聚类  
        int [] assignMeans = new int[tsLength];//记录所有点属于的聚类序号，初始化全部为0  
        Map<Integer, Vector<Integer>> clusterMember = new TreeMap<Integer,Vector<Integer>>();//记录每个聚类的成员点序号  
        Vector<Integer> mem = new Vector<Integer>();  
        int iterNum = 0;//迭代次数  
        while(true){  
            System.out.println("Iteration No." + (iterNum++) + "----------------------");  
            //3、计算每个点和每个聚类中心的距离  
            for(int i = 0; i < tsLength; i++){  
                for(int j = 0; j < K; j++){  
                    distance[i][j] = getDistance(allTestSampleMap.get(testSampleNames[i]),meansMap.get(j));  
                }  
            }  
            //4、找出每个点最近的聚类中心  
            int[] nearestMeans = new int[tsLength];  
            for(int i = 0; i < tsLength; i++){  
                nearestMeans[i] = findNearestMeans(distance, i);
                System.out.println(nearestMeans[i]);
            }  
            //5、判断当前所有点属于的聚类序号是否已经全部是其离得最近的聚类，如果是或者达到最大的迭代次数，那么结束算法  
            int okCount = 0;  
            for(int i = 0; i <tsLength; i++){  
                if(nearestMeans[i] == assignMeans[i]) okCount++;  
            }  
            System.out.println("okCount = " + okCount);  
            if(okCount == tsLength || iterNum >= 10) break;  
            //6、如果前面条件不满足，那么需要重新聚类再进行一次迭代，需要修改每个聚类的成员和每个点属于的聚类信息  
            clusterMember.clear();  
            for(int i = 0; i < tsLength; i++){  
                assignMeans[i] = nearestMeans[i];  
                if(clusterMember.containsKey(nearestMeans[i])){  
                    clusterMember.get(nearestMeans[i]).add(i);    
                }  
                else {  
                    mem.clear();  //向量mem用作计算的存储。
                    mem.add(i);  
                    Vector<Integer> tempMem = new Vector<Integer>();  
                    tempMem.addAll(mem);  
                    clusterMember.put(nearestMeans[i], tempMem);  
                }  
            }  
            //7、重新计算每个聚类的中心点!  
            for(int i = 0; i < K; i++){  
                if(!clusterMember.containsKey(i)){//注意kmeans可能产生空聚类  
                    continue;  
                }  
                Map<String, Double> newMean = computeNewMean(clusterMember.get(i), allTestSampleMap, testSampleNames);  
                Map<String, Double> tempMean = new TreeMap<String, Double>();  
                tempMean.putAll(newMean);  
                meansMap.put(i, tempMean);  
            }  
        }  
        //8、形成聚类结果并且返回  
        Map<String, Integer> resMap = new TreeMap<String, Integer>();  
        for(int i = 0; i < tsLength; i++){  
            resMap.put(testSampleNames[i], assignMeans[i]);  
        }  
        return resMap;  
    }  
  
    /**计算当前聚类新的中心，采用向量平均 
     * @param clusterM 类序号，及类序号中的文档编号
     * @param allTestSampleMap 所有测试样例的<文件名，向量>构成的map 
     * @param testSampleNames 所有测试样例文件名构成的数组 
     * @return Map<String, Double> 新的聚类中心的向量 
     * @throws IOException  
     */  
    private Map<String, Double> computeNewMean(Vector<Integer> clusterM,  
            Map<String, Map<String, Double>> allTestSampleMap,  
            String[] testSampleNames) {  
        // TODO Auto-generated method stub  
        double memberNum = (double)clusterM.size();  
        Map<String, Double> newMeanMap = new TreeMap<String,Double>();  
        Map<String, Double> currentMemMap = new TreeMap<String,Double>();  
        for(Iterator<Integer> it = clusterM.iterator(); it.hasNext();){  //迭代每个文档
            int me = it.next();  
            currentMemMap = allTestSampleMap.get(testSampleNames[me]);   //取当前文档的向量
            Set<Map.Entry<String, Double>> currentMemMapSet = currentMemMap.entrySet();  
            for(Iterator<Map.Entry<String, Double>> jt = currentMemMapSet.iterator(); jt.hasNext();){   //迭代当前向量
                Map.Entry<String, Double> ne = jt.next();  
                if(newMeanMap.containsKey(ne.getKey())){  //如果新的包含，则值相加
                    newMeanMap.put(ne.getKey(), newMeanMap.get(ne.getKey()) + ne.getValue());  
                }   
                else {  //如果新的不包含，则值不相加，即对于第一次迭代时
                    newMeanMap.put(ne.getKey(), ne.getValue());  
                }  
            }  
        }  
          
        Set<Map.Entry<String, Double>> newMeanMapSet = newMeanMap.entrySet();  //更新新的聚类中心点，即把向量中心点平均
            for(Iterator<Map.Entry<String, Double>> jt = newMeanMapSet.iterator(); jt.hasNext();){  
                Map.Entry<String, Double> ne = jt.next();  
                newMeanMap.put(ne.getKey(), newMeanMap.get(ne.getKey()) / memberNum);     
        }  
        return newMeanMap;  
    }  
  
    /**找出距离当前点最近的聚类中心 ,基于余弦夹角计算，那就是相似度最高的点
     * @param double[][] 点到所有聚类中心的距离 
     * @return i 最近的聚类中心的序 号 
     * @throws IOException  
     */  
    private int findNearestMeans(double[][] distance,int m) {  
        // TODO Auto-generated method stub  
        /*double minDist = 10;  
        int j = 0;  
        for(int i = 0; i < distance[m].length; i++){  
            if(distance[m][i] < minDist){  
                minDist = distance[m][i];  
                j = i;  
            }  
        }  
        return j;  */
        double maxDist = 0;  
        int j = 0;  
        for(int i = 0; i < distance[m].length; i++){  
            if(distance[m][i] > maxDist){  
            	maxDist = distance[m][i];  
                j = i;  
            }  
        }  
        return j;  
    }  
  
  
    /**计算两个点的余弦相似度距离 
     * @param map1 点1的向量map 
     * @param map2 点2的向量map 
     * @return double 两个点的距离 ,
     */  
    private double getDistance(Map<String, Double> map1, Map<String, Double> map2) {  
        // TODO Auto-generated method stub  
        return computeSim(map1,map2);  
    }  
     
    /**计算两个点的欧式距离 
     * @param map1 点1的向量map 
     * @param map2 点2的向量map 
     * @return double 两个点的距离 
     */  
    private double getEuclideanDistance(Map<String, Double> map1, Map<String, Double> map2) {  
        // TODO Auto-generated method stub  
        return computeEuclideanDistance(map1,map2);  
    }  
    
    /**计算两个文本的相似度 ，使用余弦相似度的方法计算
     * @param testWordTFMap 文本1的<单词,词频>向量 ，在此使用的是单词，权重，即tf*idf
     * @param trainWordTFMap 文本2<单词,词频>向量  ，在此使用的是单词，权重，即tf*idf
     * @return Double 向量之间的相似度 以向量夹角余弦计算或者向量内积计算（效果相当而速度更快） 
     * @throws IOException  
     */  
    private double computeSim(Map<String, Double> testWordTFMap,  
            Map<String, Double> trainWordTFMap) {  
        // TODO Auto-generated method stub  
        double mul = 0;//, testAbs = 0, trainAbs = 0;
        double xAbs = 0, yAbs = 0;
        Set<Map.Entry<String, Double>> testWordTFMapSet = testWordTFMap.entrySet();  
        for(Iterator<Map.Entry<String, Double>> it = testWordTFMapSet.iterator(); it.hasNext();){  
            Map.Entry<String, Double> me = it.next();  
            if(trainWordTFMap.containsKey(me.getKey())){  
                mul += me.getValue()*trainWordTFMap.get(me.getKey());  
            }  
            xAbs += me.getValue() * me.getValue(); 
            yAbs += trainWordTFMap.get(me.getKey()) * trainWordTFMap.get(me.getKey());
        } 
        xAbs = Math.sqrt(xAbs);
        yAbs = Math.sqrt(yAbs);
        //testAbs = Math.sqrt(testAbs);  
          
        /*Set<Map.Entry<String, Double>> trainWordTFMapSet = trainWordTFMap.entrySet(); 
        for(Iterator<Map.Entry<String, Double>> it = trainWordTFMapSet.iterator(); it.hasNext();){ 
            Map.Entry<String, Double> me = it.next(); 
            trainAbs += me.getValue()*me.getValue(); 
        } 
        trainAbs = Math.sqrt(trainAbs);*/  
        return mul/( xAbs * yAbs);/// (testAbs * trainAbs);  
    }
    
    /**计算两个文本的相似度 ，使用欧式距离的方法计算
     * @param testWordTFMap 文本1的<单词,词频>向量 ，在此使用的是单词，权重，即tf*idf
     * @param trainWordTFMap 文本2<单词,词频>向量  ，在此使用的是单词，权重，即tf*idf
     * @return Double 向量之间的相似度 以向量夹角余弦计算或者向量内积计算（效果相当而速度更快） 
     * @throws IOException  
     */  
	private double computeEuclideanDistance(Map<String, Double> testWordTFMap,  
            Map<String, Double> trainWordTFMap) {  
        // TODO Auto-generated method stub  
        double mul = 0;//, testAbs = 0, trainAbs = 0;
        Set<Map.Entry<String, Double>> testWordTFMapSet = testWordTFMap.entrySet();  
        for(Iterator<Map.Entry<String, Double>> it = testWordTFMapSet.iterator(); it.hasNext();){  
            Map.Entry<String, Double> me = it.next();  
            if(trainWordTFMap.containsKey(me.getKey())){  
                mul += (me.getValue()-trainWordTFMap.get(me.getKey()))*(me.getValue()-trainWordTFMap.get(me.getKey()));  
            }  
        } 
        //testAbs = Math.sqrt(testAbs); 
        return Math.sqrt(mul);/// (testAbs * trainAbs);  
    }
	/**计算两个文本的相似度 ，使用向量内积的方法计算
     * @param testWordTFMap 文本1的<单词,词频>向量 
     * @param trainWordTFMap 文本2<单词,词频>向量  
     * @return Double 向量之间的相似度 以向量夹角余弦计算或者向量内积计算（效果相当而速度更快） 
     * @throws IOException  
     */  
	private double computeSimVector(Map<String, Double> testWordTFMap,  
            Map<String, Double> trainWordTFMap) {  
		// TODO Auto-generated method stub  
        double mul = 0;//, testAbs = 0, trainAbs = 0;
        Set<Map.Entry<String, Double>> testWordTFMapSet = testWordTFMap.entrySet();  
        for(Iterator<Map.Entry<String, Double>> it = testWordTFMapSet.iterator(); it.hasNext();){  
            Map.Entry<String, Double> me = it.next();  
            if(trainWordTFMap.containsKey(me.getKey())){  
                mul += me.getValue()*trainWordTFMap.get(me.getKey());  
            }
        } 
        //testAbs = Math.sqrt(testAbs);  
          
        /*Set<Map.Entry<String, Double>> trainWordTFMapSet = trainWordTFMap.entrySet(); 
        for(Iterator<Map.Entry<String, Double>> it = trainWordTFMapSet.iterator(); it.hasNext();){ 
            Map.Entry<String, Double> me = it.next(); 
            trainAbs += me.getValue()*me.getValue(); 
        } 
        trainAbs = Math.sqrt(trainAbs);*/  
        return mul;/// (testAbs * trainAbs);  
    }
    /**获取kmeans算法迭代的初始点 ，均匀获取的方法
     * @param k 聚类的数量 
     * @param Map<String, Map<String, Double>> allTestSampleMap 所有测试样例的<文件名，向量>构成的map 
     * @return Map<Integer, Map<String, Double>> 初始中心点的Map 
     * @throws IOException  
     */  
    private Map<Integer, Map<String, Double>> getInitPoint(Map<String, Map<String, Double>> allTestSampleMap, int K) {  
        // TODO Auto-generated method stub  
        int count = 0, i = 0;  
        Map<Integer, Map<String, Double>> meansMap = new TreeMap<Integer, Map<String, Double>>();//保存K个聚类中心点向量  
        //System.out.println("本次聚类的初始点对应的文件为：");  
        Set<Map.Entry<String, Map<String,Double>>> allTestSampleMapSet = allTestSampleMap.entrySet();  
        for(Iterator<Map.Entry<String, Map<String,Double>>> it = allTestSampleMapSet.iterator();it.hasNext();){  
            Map.Entry<String, Map<String,Double>> me = it.next();  
            if(count == i * allTestSampleMapSet.size() / K){  
                meansMap.put(i, me.getValue());  
                System.out.println(me.getKey() + " map size is " + me.getValue().size());  
                i++;  
            }  
            count++;  
        }  
        return meansMap;  
    }  
  
    /**输出聚类结果到文件中 
     * @param kmeansClusterResultFile 输出文件目录 
     * @param kmeansClusterResult 聚类结果 
     * @throws IOException  
     */  
    private void printClusterResult(Map<String, Integer> kmeansClusterResult, String kmeansClusterResultFile) throws IOException {  
        // TODO Auto-generated method stub  
        FileWriter resWriter = new FileWriter(kmeansClusterResultFile);
        Set<Map.Entry<String,Integer>> kmeansClusterResultSet = kmeansClusterResult.entrySet();  
        for(Iterator<Map.Entry<String,Integer>> it = kmeansClusterResultSet.iterator(); it.hasNext(); ){  
            Map.Entry<String, Integer> me = it.next();  
            resWriter.append(me.getKey() + " " + me.getValue() + "\n");  
        }  
        resWriter.flush();  
        resWriter.close();  
    }  
      
    public void KmeansClusterMain(String path) throws Exception {  
        //首先计算文档TF-IDF向量，保存为Map<String,Map<String,Double>> 即为Map<文件名，Map<特征词，TF-IDF值>>  
    	WordSplit wordSplit=new WordSplit();
        int[] K = {10, 20 ,30};
        ArrayList listMap=new ArrayList<>();
        listMap=wordSplit.computeTFMultiIDF();
        Map<String,Map<String,Double>> allTestSampleMap = (Map<String, Map<String, Double>>) listMap.get(2);  
        double totalSim=0;
        int clusterNum=0;
        for(int i = 2; i < 3; i++){  
            System.out.println("开始聚类，聚成" + i + "类");  
            //String KmeansClusterResultFile = "F:/DataMiningSample/KmeansClusterResult/";  
            Map<Integer, HashSet<String>> KmeansClusterResult2 = new TreeMap<Integer, HashSet<String>>();
            //KmeansClusterResult2 = kmeansResult(allTestSampleMap, K[i]);
		    KmeansClusterResult2 = kmeansResult(allTestSampleMap, i);
            /*for (Integer numCluster:KmeansClusterResult2.keySet()) {
            	System.out.println(KmeansClusterResult2.get(numCluster).size()); 
            	System.out.println(KmeansClusterResult2.get(numCluster)); 
			}*/
            System.out.println(KmeansClusterResult2.size()); 
            System.out.println("开始进行聚类结果评估");  
            System.out.println("+++++++++++++++"+wordSplit.evaluateClusterResult(KmeansClusterResult2, allTestSampleMap)); 
            System.out.println("聚类结果评估结束");
          double currentSim=wordSplit.evaluateClusterResult(KmeansClusterResult2, allTestSampleMap);
          if (currentSim>totalSim) {
			totalSim=currentSim;
			clusterNum=i;
		}
        }
        Map<Integer, HashSet<String>> KmeansClusterResultLast = new TreeMap<Integer, HashSet<String>>();
        KmeansClusterResultLast=kmeansResult(allTestSampleMap, clusterNum);
       
        //定义嵌套map函数，
      	//String为文档ID，其中的MAP为文档的词及词频数目
      	Map<String, Map<String, Integer>> map4=(Map<String, Map<String, Integer>>) listMap.get(0);		
      	//倒排序文档集，统计每个词出现的文档集合
      	Map<String, HashSet<String>> map5=(Map<String, HashSet<String>>) listMap.get(1);
        //统计每个文档的总词语数
      	Map<String, Integer> map2 = (Map<String, Integer>) listMap.get(3);
      	ArrayList list=wordSplit.computeTopWords(KmeansClusterResultLast,100,map4,map5,map2);
        wordSplit.writeToJson(path, list);
    }

	private Map<Integer, HashSet<String>> kmeansResult(Map<String, Map<String, Double>> allTestSampleMap, int K) {
		//定义的返回map，map里面记录每个类的编号，类内的成员
		Map<Integer, HashSet<String>> KmeansClusterResult3 = new TreeMap<Integer,HashSet<String>>();
		//0、首先获取allTestSampleMap所有文件名顺序组成的数组  
		String[] testSampleNames = new String[allTestSampleMap.size()];
		if (K==1) {
			HashSet<String> strings=new HashSet<String>();
			for (String str:allTestSampleMap.keySet()) {
				strings.add(str);
			}
			KmeansClusterResult3.put(1, strings);
		} else {
			int count = 0, tsLength = allTestSampleMap.size();
			
			//把map转换成集合，用于迭代
			Set<Map.Entry<String, Map<String, Double>>> allTestSampeleMapSet = allTestSampleMap.entrySet();
			
			//迭代获取map里面的键值对，其中testSampleNames数组用于记录map里面的Key值，即每个文档的ID
			for (Iterator<Map.Entry<String, Map<String, Double>>> it = allTestSampeleMapSet.iterator(); it.hasNext();) {
				Map.Entry<String, Map<String, Double>> me = it.next();
				testSampleNames[count++] = me.getKey();
			}
			
			//1、初始点的选择算法是随机选择或者是均匀分开选择，这里采用后者  
	        Map<Integer, Map<String, Double>> meansMap = getInitPoint(allTestSampleMap, K);//保存K个中心点  
	        
	        double[][] distance = new double[tsLength][K];//distance[i][j]记录点i到聚类中心j的距离  
	        //2、初始化K个聚类  
	        int[] assignMeans = new int[tsLength];//记录所有点属于的聚类序号，初始化全部为0
	        //Vector 本身就是可实现自动增长的对象数组。
	        //java.util.vector提供了向量类(vector)以实现类似动态数组的功能。
	        //在Java语言中没有指针的概念，但如果正确灵活地使用指针又确实可以大大提高程序的质量。
	        //比如在c,c++中所谓的“动态数组”一般都由指针来实现。为了弥补这个缺点，Java提供了丰富的类库来方便编程者使用，vector类便是其中之一。
	        //事实上，灵活使用数组也可以完成向量类的功能，但向量类中提供大量的方法大大方便了用户的使用。
	        Map<Integer, Vector<Integer>> clusterMember = new TreeMap<Integer,Vector<Integer>>();//记录每个聚类的成员点序号  
	        Vector<Integer> mem = new Vector<Integer>(); 
	        int iterNum = 0;//迭代次数  
	        while(true){  
	            //System.out.println("Iteration No." + (iterNum++) + "----------------------");  
	            //3、计算每个点和每个聚类中心的距离  
	            for(int i = 0; i < tsLength; i++){  //每个点
	                for(int j = 0; j < K; j++){  //每个聚类中心
	                    distance[i][j] = getDistance(allTestSampleMap.get(testSampleNames[i]),meansMap.get(j));  
	                }  
	            }  
	            //4、找出每个点最近的聚类中心,即每个类别的序号  
	            int[] nearestMeans = new int[tsLength];  
	            for(int i = 0; i < tsLength; i++){  
	                nearestMeans[i] = findNearestMeans(distance, i);  
	            }  
	            //5、判断当前所有点属于的聚类序号是否已经全部是其离得最近的聚类，即当前聚类中心是否在改变，如果没有改变或者达到最大的迭代次数，那么结束算法  
	            int okCount = 0;  
	            for(int i = 0; i <tsLength; i++){  
	                if(nearestMeans[i] == assignMeans[i]) okCount++;  
	            }  
	            System.out.println("okCount = " + okCount);  
	            if(okCount == tsLength || iterNum >= 20) break;  
	            //6、如果前面条件不满足，那么需要重新聚类再进行一次迭代，需要修改每个聚类的成员和每个点属于的聚类信息 
	            //对于第一次，就是将各个成员划分到初始的聚类中心里面去
	            clusterMember.clear(); 
	            KmeansClusterResult3.clear();
	            for(int i = 0; i < tsLength; i++){  
	                assignMeans[i] = nearestMeans[i]; //将每个点都划分到计算的距离最近的点属于的聚类序号
	                if(KmeansClusterResult3.containsKey(nearestMeans[i])){  //如果当前聚类结果的Key包含该聚类序号
	                	HashSet<String> set=KmeansClusterResult3.get(nearestMeans[i]);
	                    set.add(testSampleNames[i]);
	                    KmeansClusterResult3.put(nearestMeans[i], set);
	                }  
	                else {  
	                	HashSet<String> set=new HashSet<String>();
	                	set.add(testSampleNames[i]);
	                	KmeansClusterResult3.put(nearestMeans[i], set);
	                }
	            }  
	            //7、重新计算每个聚类的中心点!  
	            for(int i = 0; i < K; i++){  
	                if(!clusterMember.containsKey(i)){//注意kmeans可能产生空聚类  
	                    continue;  
	                }  
	                Map<String, Double> newMean = computeNewMean(clusterMember.get(i), allTestSampleMap, testSampleNames);  
	                Map<String, Double> tempMean = new TreeMap<String, Double>();  
	                tempMean.putAll(newMean);  
	                meansMap.put(i, tempMean);  
	            }  
	        }  
	        //8、形成聚类结果并且返回  
	        Map<String, Integer> resMap = new TreeMap<String, Integer>();  
	        for(int i = 0; i < tsLength; i++){  
	            resMap.put(testSampleNames[i], assignMeans[i]);  
	        }  
		}
		
        return KmeansClusterResult3; 
	}  
}
