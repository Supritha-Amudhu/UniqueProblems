import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		int[][] a = {{1,2}, {1,3}, {1,4}, {1,6}, {1,7}, {2,3}};
		int[][] b = {{3,5}, {4,5}, {2,5}, {1,2}, {2,1}, {2,2}, {2,8}, {4,8}, {6,8}, {7,8}};
		int targetUser = 1;
		int minThreshold = 3;
		getRecommendedTweets(a, b, targetUser, minThreshold);
	}
	
	static int[] getRecommendedTweets(int[][] followGraph_edges, int[][] likeGraph_edges, 
            int targetUser, int minLikeThreshold) {
		int[] result = new int[0];
		if(followGraph_edges.length==0 || likeGraph_edges.length==0){
			return result;
		}
		Map<Integer, ArrayList<Integer>> edges_link = assign_edges(followGraph_edges, 0);
			System.out.println(Collections.singletonList(edges_link));
		Map<Integer, ArrayList<Integer>> likes_link = assign_edges(likeGraph_edges, 1);
			System.out.println(Collections.singletonList(likes_link));
			ArrayList<Integer> recommendedTweets = findRecommendedTweets(targetUser, minLikeThreshold, edges_link, likes_link);
			System.out.println(recommendedTweets);
			result = recommendedTweets.stream().mapToInt(i->i).toArray();
		return result;		
}
	private static ArrayList<Integer> findRecommendedTweets(int targetUser, int minLikeThreshold, Map<Integer, ArrayList<Integer>> edges_link, Map<Integer, ArrayList<Integer>> likes_link){
		ArrayList<Integer> recommendedTweets = new ArrayList<>();
		for(Integer i:likes_link.keySet()){
			int size =likes_link.get(i).size();
			if(likes_link.get(i).size()>=minLikeThreshold){
				if(checkMinThreshold(targetUser, minLikeThreshold, edges_link.get(targetUser), likes_link.get(i))){
					recommendedTweets.add(i);
				}
			}
		}
		return recommendedTweets;
		
	}
	private static Boolean checkMinThreshold(int targetUser, int minLikeThreshold, ArrayList<Integer> follows, ArrayList<Integer> liked_by){
		Boolean result = false;
//			System.out.println(follows);
//			System.out.println(liked_by);
		int count = 0;
		for(int i=0;i<follows.size();i++){
			for(int j=0;j<liked_by.size();j++){
				if(follows.get(i)==liked_by.get(j)){
					count++;
					if(count==minLikeThreshold){
						result=true;
						break;
					}
				}
			}
		}
		if(count<minLikeThreshold){
			result=false;
		}
		return result;	
	}
	
	private static Map<Integer, ArrayList<Integer>> assign_edges(int[][] edges, int flag){
		Map<Integer, ArrayList<Integer>> resultHashMap = new HashMap<>();
		for(int i=0;i<edges.length;i++){
			ArrayList<Integer> valueArrayList = new ArrayList<>();
			Integer key = new Integer(0);
			Integer value = new Integer(0);
			if(flag==0){
				key = edges[i][0];
				value = edges[i][1];
			}
			else{
				key = edges[i][1];
				value = edges[i][0];
			}
			if(resultHashMap.containsKey(key)){
				valueArrayList = resultHashMap.get(key);
				valueArrayList.add(value);
				resultHashMap.put(key, valueArrayList);
			}
			else{
				valueArrayList.add(value);
				resultHashMap.put(key,valueArrayList);
			}
		}
		return resultHashMap;
		
	}
	
	

}
