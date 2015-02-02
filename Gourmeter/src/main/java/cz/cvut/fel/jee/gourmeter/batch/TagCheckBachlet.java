package cz.cvut.fel.jee.gourmeter.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Inject;
import javax.inject.Named;

import cz.cvut.fel.jee.gourmeter.bo.Tag;
import cz.cvut.fel.jee.gourmeter.ejb.DataSessionLocal;


@Named
public class TagCheckBachlet extends AbstractBatchlet
{
	@Inject
	DataSessionLocal data;

	@Override
	public String process() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Integer> result = new HashMap<>();
		Map<String,Integer> duplicates = new HashMap<>();
		List<Tag> d = this.data.getAllTags();		

		for (int i = 0; i < d.size(); i++) 
		{
			if (result.containsKey(d.get(i).getName()))
			{
				if (duplicates.containsKey(d.get(i).getName()))
				{
					duplicates.put(d.get(i).getName(), duplicates.get(d.get(i).getName()) + 1);
				}
				else duplicates.put(d.get(i).getName(), 1);
			}
			else result.put(d.get(i).getName(), 1);
		}
		if (duplicates.size() > 0) return "DUPLICATES FOUND";
		else return "COMPLETED";
	}

}
