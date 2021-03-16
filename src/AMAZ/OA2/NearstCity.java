package AMAZ.OA2;

import java.util.*;

public class NearstCity {
    //刀的写法
    public static class City {
        City(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public int dist(City other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }

        public String name;
        public int x, y;
    }

    public static String[] findNearestCities(int numOfPoints, String[] points, int[] xCoordinates, int[] yCoordinates, int numOfQueries, String[] queries) {
        Map<Integer, TreeSet<City>> sameX = new HashMap<>();
        Map<Integer, TreeSet<City>> sameY = new HashMap<>();
        Map<String, City> cities = new HashMap<>();
        for (int i = 0; i < numOfPoints; ++i) {
            int curX = xCoordinates[i], curY = yCoordinates[i];
            String name = points[i];
            City c = new City(name, curX, curY);
            cities.put(points[i], c);
            if (!sameX.containsKey(curX)) {
                sameX.put(curX, new TreeSet<>((a, b) -> a.y - b.y));
            }
            sameX.get(curX).add(c);

            if (!sameY.containsKey(curY)) {
                sameY.put(curY, new TreeSet<>((a, b) -> a.x - b.x));
            }
            sameY.get(curY).add(c);
        }

        String[] result = new String[numOfQueries];

        for (int i = 0; i < numOfQueries; ++i) {
            City cur = cities.get(queries[i]);
            List<City> candidates = new ArrayList<>();
            City left_sameX = sameX.get(cur.x).lower(cur);
            City right_sameX = sameX.get(cur.x).higher(cur);
            City left_sameY = sameY.get(cur.y).lower(cur);
            City right_sameY = sameY.get(cur.y).higher(cur);
            if (left_sameX != null) candidates.add(left_sameX);
            if (right_sameX != null) candidates.add(right_sameX);
            if (left_sameY != null) candidates.add(left_sameY);
            if (right_sameY != null) candidates.add(right_sameY);
            if (candidates.size() == 0) {
                result[i] = "NONE";
                continue;
            }
//            candidates.Sort(Comparator.comparingInt((City a) -> a.dist(cur)).thenComparing(City::getName));
            int smallestDist = Integer.MAX_VALUE;
            String nearstName = cur.name;
            for (City c : candidates) {
                if (c.dist(cur) < smallestDist) {
                    smallestDist = c.dist(cur);
                    nearstName = c.name;
                }
                if (c.dist(cur) == smallestDist && c.name.compareTo(nearstName) < 0) {
                    smallestDist = c.dist(cur);
                    nearstName = c.name;
                }
            }
            result[i] = nearstName;
        }



        return result;
    }

//    public static void main(String[] args) {
//        int numOfPoints = 4;
//        String[] cities = new String[] {"c1", "c2", "c4", "c3"};
//        int[] xCoordinates = new int[] { 3, 2, 1, 5};
//        int[] yCoordinates = new int[] { 3, 2, 3, 3};
//        int numOfQueries= 3;
//        String[] queries = new String[] {"c1", "c2", "c3"};
//        String[] result = findNearestCities(numOfPoints, cities, xCoordinates, yCoordinates,
//                numOfQueries, queries);
//        for (String s : result) {
//            System.out.println(s);
//        }
//    }



    //用TreeSet写的

    private static List<String> nearstCity(int numOfCities, List<String> cities, int[] xCoordinates, int[] yCoordinates, int numOfQueries, List<String> queries){
        Map<Integer, TreeSet<Integer>> sameX = new HashMap<>();
        Map<Integer, TreeSet<Integer>> sameY = new HashMap<>();
        Map<String, Integer> idMap = new HashMap<>();
        Map<int[], String> mapForCoordinates = new HashMap<>();
        List<String> res = new ArrayList<>();


        for(int i = 0; i < numOfCities; i++){
            int curX = xCoordinates[i], curY = yCoordinates[i];
            String name = cities.get(i);
            idMap.put(name, i);
            mapForCoordinates.put(new int[]{curX, curY}, name);

            if(!sameX.containsKey(curX)) sameX.put(curX, new TreeSet<>((a, b) -> yCoordinates[a] - yCoordinates[b]));
            sameX.get(curX).add(i);

            if(!sameY.containsKey(curY)) sameY.put(curY, new TreeSet<>((a, b) -> xCoordinates[a] - xCoordinates[b]));
            sameX.get(curY).add(i);
        }

        for(int i = 0; i < numOfQueries; i++){
            String queryName = queries.get(i);
            int queryID = idMap.get(queryName);
            int curX = xCoordinates[queryID], curY = yCoordinates[queryID];

            TreeSet<Integer> sameXCoordinates = sameX.get(curX);
            int smallerY = Integer.MAX_VALUE, higherY = Integer.MAX_VALUE;
            if(sameXCoordinates.lower(curY) != null) smallerY = sameXCoordinates.lower(curY);
            if(sameXCoordinates.higher(curY) != null) higherY = sameXCoordinates.higher(curY);
            int closestY = Math.abs(smallerY - curY) <= Math.abs(higherY - curY) ? smallerY : higherY;
            int[] sameXCoordinate = new int[]{curX, closestY};

            TreeSet<Integer> sameYCoordinates = sameY.get(curY);
            int smallerX = Integer.MAX_VALUE, higherX = Integer.MAX_VALUE;
            if(sameYCoordinates.lower(curX) != null) smallerX = sameYCoordinates.lower(curX);
            if(sameYCoordinates.higher(curX) != null) higherX = sameYCoordinates.higher(curX);
            int closestX = Math.abs(smallerX - curX) <= Math.abs(higherX - curX) ? smallerX : higherX;
            int[] sameYCoordinate = new int[]{closestX, curY};

            int[] overallClosest = Math.abs(closestY - curY) <= Math.abs(closestX - curX)? sameXCoordinate : sameYCoordinate;

            if(mapForCoordinates.containsKey(overallClosest)) res.add(mapForCoordinates.get(overallClosest));
            else res.add("NONE");
        }


        return res;


    }







    //用priorityQueue写的
    private static List<String> nearstCity_pq(int numOfCities, List<String> cities, int[] xCoordinates, int[] yCoordinates, int numOfQueries, List<String> queries){
        List<String> res = new ArrayList<>();
        for(int i = 0; i < numOfQueries; i++){
            //找当前的的query对应的x， y
            String curName = queries.get(i);
            int curID = -1;

            for(int k = 0; k < cities.size(); i++){
                if(cities.get(k).equals(curName)) curID = k;
            }
            if(curID == -1){
                res.add("NONE");
                continue;
            }
            int queryX = xCoordinates[curID], queryY = yCoordinates[curID];

            //找最近的query
            Queue<Integer> nearCities = new PriorityQueue<>((a, b) -> Math.abs(xCoordinates[a] - queryX) + Math.abs(yCoordinates[a] - queryY));
            for(int j = 0; j < numOfCities; j++){
                if(cities.get(j) != curName && (xCoordinates[j] == queryX || yCoordinates[j] == queryY )){
                    nearCities.offer((j));
                }
            }

            if(nearCities.isEmpty()) {
                res.add("NONE");
                continue;
            }
            res.add(cities.get(nearCities.poll()));

        }

        return res;


    }

}
