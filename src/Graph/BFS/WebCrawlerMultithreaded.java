package Graph.BFS;

import java.util.*;

public class WebCrawlerMultithreaded {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        if(startUrl.length() < 1) return new ArrayList<>();
        Set<String> res = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int end = 0, cnt = 0;
        for(int j = 0; j < startUrl.length(); j++){
            if(startUrl.charAt(j) == '/') cnt++;
            if(cnt == 2) end = j+1;
        }
        String hostName = end == 0 ? startUrl : startUrl.substring(0, end);
        queue.offer(startUrl);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String cur = queue.poll();
                if(!res.contains(cur) && cur.startsWith(hostName)) res.add(cur);
                List<String> neighbours = htmlParser.getUrls(cur);
                for(String url : neighbours){
                    queue.offer(url);
                }
            }
        }
        return new ArrayList<>(res);
    }

    class HtmlParser {
      public List<String> getUrls(String url) {
          return new ArrayList<>();
      }
    }



    public List<String> crawl_2(String startUrl, HtmlParser htmlParser) {

        // find hostname
        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

        // multi-thread
        Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
        crawler.result = new HashSet<>(); // reset result as static property belongs to class, it will go through all of the test cases
        Thread thread = new Thread(crawler);
        thread.start();

        crawler.joinThread(thread); // wait for thread to complete
        return new ArrayList<>(crawler.result);
    }
}

    class Crawler implements Runnable {
        String startUrl;
        String hostname;
        WebCrawlerMultithreaded.HtmlParser htmlParser;
        public static volatile Set<String> result = new HashSet<>();

        public Crawler(String startUrl, String hostname, WebCrawlerMultithreaded.HtmlParser htmlParser){
            this.startUrl = startUrl;
            this.hostname = hostname;
            this.htmlParser = htmlParser;
        }
        @Override
        public void run(){
            if(this.startUrl.startsWith(hostname) && !this.result.contains(this.startUrl)){
                addUrl(this.result, this.startUrl);
                List<Thread> threads = new ArrayList<>();
                for(String s: htmlParser.getUrls(startUrl)){
                    if(result.contains(s)) continue;
                    Crawler crawler = new Crawler(s, hostname, htmlParser);
                    Thread thread = new Thread(crawler);
                    thread.start();
                    threads.add(thread);
                }
                for(Thread t: threads){
                    joinThread(t); // wait for all threads to complete
                }
            }
        }
        public static synchronized void addUrl(Set<String> result, String url){
            result.add(url);
        }

        public static void joinThread(Thread thread){
            try{
                thread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
}
