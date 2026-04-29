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
    // 0 : (10-0) + (20-10) + (30-20) =30 //  1 : (25-15) =10


    /// for every meeting we need to check if we have free room for it
    /// if yes : we take this rooms and add it to usedRooms with room index and when it ll be free
    /// if no : we delay this meeting until rooms is free that means : check usedRooms for the soonest one
    /// can be free so we take it and change his end time
    static int roomMostUsed(int[][] meetings,int roomsNumber){
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> freeRooms=new PriorityQueue<>();
        PriorityQueue<int[]> usedRooms=new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        HashMap<Integer,Integer> roomUsedTime=new HashMap<>();
        int maxTimeUsed=Integer.MIN_VALUE;
        int roomMostedUsedIndex=0;
        for(int i=0;i<roomsNumber;i++){
            freeRooms.offer(i);
        }
        usedRooms.offer(new int[]{freeRooms.poll(),meetings[0][1]});
        for(int i=1; i<meetings.length ; i++){
            if(meetings[i][0]>=usedRooms.peek()[1]){
                freeRooms.offer(usedRooms.poll()[0]);
            }
            if(!freeRooms.isEmpty()){
                int roomIndex=freeRooms.poll();
                usedRooms.offer(new int[]{freeRooms.poll(),meetings[i][1]});
                int roomTime=roomUsedTime.getOrDefault(roomIndex,0)+(meetings[i][1]-meetings[i][0]);
                roomUsedTime.put(roomIndex,roomTime);
                if(roomTime > maxTimeUsed){
                    roomMostedUsedIndex=roomIndex;
                }
            }else {
                int[] futureAvailableRoom= usedRooms.poll();
                //usedRooms.stream().filter(a-> )
            }
        }
        return roomMostedUsedIndex;
    }

    public int mostBookedSolution(int n, int[][] meetings) {
        // 1. Must sort to process chronologically
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Tracks indices of free rooms: [0, 1, 2...]
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) freeRooms.add(i);

        // Tracks [endTime, roomIndex]. Sorted by time, then index.
        PriorityQueue<long[]> usedRooms = new PriorityQueue<>((a, b) ->
                a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])
        );

        int[] roomMeetingCount = new int[n];

        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            // RELEASE: Free all rooms that finished before this meeting starts
            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                freeRooms.add((int) usedRooms.poll()[1]);
            }

            if (!freeRooms.isEmpty()) {
                // CASE 1: Room is available now
                int room = freeRooms.poll();
                roomMeetingCount[room]++;
                usedRooms.add(new long[]{end, room});
            } else {
                // CASE 2: No room free, wait for the soonest one
                long[] soonest = usedRooms.poll();
                long newStart = soonest[0];
                int room = (int) soonest[1];

                roomMeetingCount[room]++;
                // The meeting is delayed, so its new end is (roomFreeTime + originalDuration)
                usedRooms.add(new long[]{newStart + duration, room});
            }
        }

        // Find the winner
        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (roomMeetingCount[i] > roomMeetingCount[maxIdx]) maxIdx = i;
        }
        return maxIdx;
    }
}
