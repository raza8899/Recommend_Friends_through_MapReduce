import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MutualReducer extends Reducer<IntWritable,ImpArrayWritable,IntWritable,Text>{
	
	public void reduce(IntWritable key ,Iterable<ImpArrayWritable> values,Context context) throws IOException, InterruptedException
	{
		HashMap<IntWritable,Integer> unknowns=new HashMap<IntWritable,Integer>();
		HashMap<IntWritable, Integer>  friends =new HashMap<IntWritable,Integer>();
		
		
		for(ImpArrayWritable tuple:values)
		{
		
			IntWritable[] tuples=(IntWritable[])tuple.toArray();
			IntWritable fb_user=tuples[0];
			IntWritable relation=tuples[1];

			if(relation.get()==0)
				friends.put(fb_user, relation.get());
			
			else
			{
				if(unknowns.containsKey(fb_user))
					unknowns.put(fb_user,unknowns.get(fb_user)+1);
				else
					unknowns.put(fb_user, 1);
				
			}
		}
		
		for(IntWritable friend:friends.keySet())
		{
			
			
				unknowns.remove(friend);
		}
		KeyComparator comp=new KeyComparator(unknowns);
		TreeMap<IntWritable, Integer> forsorting=new TreeMap<>(comp);
		forsorting.putAll(unknowns);
		int incr=0;
		StringBuilder finalout =new StringBuilder("");
		for(Entry<IntWritable,Integer>  entries :forsorting.entrySet())
		{
			finalout.append(entries.getKey().toString());
			if(incr==forsorting.size()-1||incr==9)
				break;
			else
			{
				finalout.append(",");
				incr++;
			}
			
		}
		Text t =new Text();
		t.set(finalout.toString());
		context.write(key, t);
		
		
		
	}
	

}
class KeyComparator implements Comparator<IntWritable>
{
	HashMap<IntWritable,Integer> comp=new HashMap<IntWritable,Integer>();
	
	public KeyComparator(HashMap<IntWritable,Integer> comp)
	{
		this.comp=comp;
	}
	
	public int compare(IntWritable a ,IntWritable b)
	{
		if(comp.get(a)<comp.get(b))
			return 1;
		else if(comp.get(a)==comp.get(b))
		{
			if(a.get()>b.get())
				return 1;
			else 
				return -1;
			
		}
		else
			return -1;
		
	}
	
	



}
