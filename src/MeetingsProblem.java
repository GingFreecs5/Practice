import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MeetingsProblem    {
    public static void main(String[] args) {
        int[][] meetings={{10,20},{15,25},{20,30},{0,10}};
        // {{0,10},{10,20},{15,25},{20,30}}  here we need 2 rooms in this example
        int[][] meetings1={{10,20},{20,30},{0,10}}; // here we need 1 room

        System.out.println(overleapCheck(meetings));
        System.out.println(roomsAvailable(meetings));
    }

    static boolean overleapCheck(int[][] meetings){
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < meetings.length-1; i++) {
            if(meetings[i][1] > meetings[i+1][0]){
                return false;
            }

        }
        return true;
    }

    // {{0,10},{10,20},{15,25},{20,30}}  here we need 2 rooms in this example

    static int roomsAvailable(int[][] meetings){
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.add(meetings[0][1]);
        for(int i=1;i<meetings.length;i++){
            //if the room can be reused
            if(meetings[i][0] >=  pq.peek()){
                pq.poll();
            }
            pq.add(meetings[i][1]);
        }
        return pq.size();
    }
    //[[0, 10], [1, 5], [2, 7], [3, 4]]
    // {{0,10},{10,20},{15,25},{20,30}}
    static int roomMostUsed(int[][] meetings,int roomsNumber){
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> freeRooms=new PriorityQueue<>();
        for(int i=0;i<roomsNumber;i++){
            freeRooms.add(i);
        }

    }
}
