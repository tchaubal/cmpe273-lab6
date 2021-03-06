package edu.sjsu.cmpe.cache.client;
import com.google.common.hash.Hashing;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
         //CacheServiceInterface cache = new DistributedCacheService(
               // "http://localhost:3000");
	
	char[] vals={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

	List<CacheServiceInterface> get_serv = new ArrayList();
	get_serv.add(new DistributedCacheService("http://localhost:3000"));
	get_serv.add(new DistributedCacheService("http://localhost:3001"));
	get_serv.add(new DistributedCacheService("http://localhost:3002"));
	System.out.println(get_serv);
	
	for(int i=1;i<=vals.length;i++)
	{
	int bucket = Hashing.consistentHash(Hashing.md5().hashInt(i),
get_serv.size());	
	get_serv.get(bucket).put(i,Character.toString(vals[i-1]));
	System.out.println( "(" + i + "=>" +vals[i-1] + ")" );
	}
   }
}
