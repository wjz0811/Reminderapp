package com.wjz.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.wjz.R;
import com.wjz.backend.testimonies.TestimonyDAO;
import com.wjz.models.testimonies.Testimony;

import java.util.ArrayList;
import java.util.List;

public class TestimonyActivity extends Activity {
    private ListView testimonyListView;
    private List<Testimony> testimony_data;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testimonyactivitylayout);
    }

    @Override
    public void onResume(){
        String[] name = {"Alison McReynolds", "Jeffrey Rhodes", "Jessica Mayle","Brian Klein", "Patrick Choquette"};
        String[] country = {"Liberia","Zambia","The Gambia", "Madagascar","Ghana"};
        String[] testimony = {   		
        		"When I was a volunteer in Mauritania in 2008-2009, I lived in the middle of the Sahara desert. " +
        		"There were a lot of things with which I was concerned on a daily basis, such as would there be water that day and " +
        		"would it be too hot to have a lesson in the school garden that afternoon, but one thing I didnât anticipate as an " +
        		"issue was taking my anti-malaria medication.  In the arid, extremely hot, northern part of Mauritania, there are " +
        		"few mosquitos and, according to the CDC website, no endemic malaria so I assumed that I would not have to take " +
        		"Mefloquine after I moved from my training community in the humid south to my permanent site in the arid north. " +
        		"I was wrong. There were still reports of malaria in my community which meant that I could be exposed and might " +
        		"have been exposed during my 2 months of training so I had to continue taking Mefloquine. " +
        		"I consider myself to be a very health conscious person and prior to joining Peace Corps I had " +
        		"done my research about the side effects of prolonged exposure to malaria medication but I had decided " +
        		"that I was willing to make the sacrifice in order to be a PCV. It was more difficult than I imagined though " +
        		"because every time I took the weekly dose my body would ache and occasionally I had very vivid nightmares. I didnât " +
        		"like the way it made me feel and, eventually, I considered not taking it anymore because of where I lived and I felt " +
        		"like the probability was so low for contracting malaria it was worth the risk of not taking the medication. When I " +
        		"started to ask other volunteers about it though and actually talked to someone who had had malaria before, I realized" +
        		" that I definitely didnât want to get malaria. The long term side effects of contracting malaria can be much more " +
        		"severe than those related to Mefloquine. I set an alarm on my phone for every Thursday at the same time to remind me" +
        		" to take my medication and I never missed a dose.",
        		
        		"I served in Zambia from 2005-2007 and had quite the experience with my malaria prophylaxis.  I entered service taking " +
        		"Mefloquine (or whatever it was called), requiring a pill every 10 days.  After a few months of taking Mefloquine, " +
        		"I started having auditory hallucinations and âblack-outâ episodes where I could not remember segments of time. I called " +
        		"the PCMO to report these side effects and she recommended I stop taking that medication and start taking Doxycycline, which " +
        		"requires a daily pill.  I traveled to the capital for a medical evaluation and a new supply of Doxy.  Despite my side-effects, " +
        		"I had a clean bill of health and the hallucinations stopped once I stopped taking the Mefloquine. Taking a pill everyday was much" +
        		" easier than remembering to take one every 10th day. I made it a part of my morning routine and never (maybe once) missed a pill " +
        		"even when I was out of site or on vacation.  I was committed to not getting sick.  The one time I did get sick during my service, " +
        		"I thought it might be malaria.  I was really sick, really fast.  It turned out to be food poisoning from eating bad roast beef from " +
        		"the Subway restaurant in the capital. The side effects with Doxy were minimal, except for sun sensitivity.  My nose, cheeks, and " +
        		"shoulders got burned very easily.  I had to adapt and wear more sunscreen and different clothes.  I got badly burned a few times " +
        		"but was able to protect myself adequately with some planning and preparation.  I did not get malaria once during my service and feel " +
        		"that the the side effects were not that big of a deal compared to the consequences of the disease.",
        		
        		"I served as an education/IT volunteer in The Gambia from 2010-2012 and took mefloquine regularly throughout my service. I very " +
        		"rarely had weird or vivid dreams and didnât suffer any other side effects, so it seemed irresponsible not to take the medicine. " +
        		"There were so many health issues I had to worry about, treat, and generally live with during service â GI issues, skin rashes, " +
        		"allergies â that inviting the risk of malaria on top of that wasnât worth it. Also, how could I educate community members on using " +
        		"bed nets or other strategies to prevent malaria when I wasnât doing everything I could personally to protect myself? It seemed " +
        		"hypocritical. I was representing Peace Corps and I took that responsibility seriously, even when making a personal decision about what " +
        		"medicine to take.",
        		
        		"I served from 2010-2012 in Vondrozo, Madagascar. I took my mefloquine first and foremost to stay healthy. A healthy volunteer is " +
        		"a happy volunteer, and with all the other parasites, viruses, and other issues you have to deal with, thereâs no reason to play games " +
        		"and take risks with something as debilitatingâand deadlyâas malaria. I did experience some mild side effects, mainly in the form of intense " +
        		"dreams. They tended towards a generally positive if bizarre tilt, though. For example, my first one involved me sitting and talking with a white " +
        		"wolf for hours on end, listening as he revealed the secrets of the universeâinteresting, to say the least. Not taking the prophylaxis was honestly " +
        		"never an option in my mind. I saw the danger it posed, the disruption it caused, even the death it could lead to in the communities around me. " +
        		"I regularly traveled out of my village on field trips to more isolated hamlets, and would stay with different local families in each place. One of " +
        		"the families I stayed with had a surly matriarch, a woman of about 40 who had been very skeptical of my intentions at first but had grown to be a " +
        		"warm, welcoming, motherly figure. Towards the end of my service, I arrived in the village to discover that she had fallen ill and passed away in my " +
        		"absence. The cause? Malaria. It was a heartrending experience for me, and unimaginably worse for her family. Taking risks with malaria, with my life, " +
        		"and thinking of my family getting a call saying the unimaginable? That simply wasnât something I could ever justify allowing myself to do.",
		
                "I served in Ghana from 2005-2007 as an Science Education volunteer. I took Mephloquine for my two years and experienced many of the side effects " +
                "including the extremely confusing and disorienting  vivid dreams. I also had other rough times and dips into depression where I thought that my taking " +
                "mephloquine might have made things worse. In retrospect that probably wasn’t the case but at the time I was eager to find reasons for why I struggled " +
                "emotionally. I took my meds religiously because I wanted to do my part in eradicating this devastating disease. I was sure that with the medical support " +
                "and resources available to me that the risk of death was low for myself if I was to come down with Malaria. However, I knew that  if a mosquito bit me while " +
                "I had the disease and then went on to infect a member of my community who did not have the same resources available, it could definitely end up being deadly to " +
                "them. As a Peace Corps Volunteer I am dedicated to eliminating Malaria throughout the world and I refuse be a carrier and enabler of the continuation of this disease. " +
                "The side effects I experienced were part of my sacrifice towards helping end this disease once and for all."
        };
        
        List<Testimony> testimony_list = new ArrayList<Testimony>();
        for(int i=0; i < 5; i++){
          Testimony t = new Testimony();
          t.setName(name[i]);
          t.setCountry(country[i]);
          t.setTestimony(testimony[i]);
          testimony_list.add(t);
        }
        
        ListView listView = (ListView) findViewById(R.id.testimonylistview);
        // testimonyListView = (ListView) findViewById(R.id.testimonylistview);

        // fetch all the testimonies and put them into a list
        // TestimonyDAO tDao = new TestimonyDAO(getApplicationContext());
        // testimony_data = tDao.getAllTestimonies();

        TestimonyListAdapter adapter = new TestimonyListAdapter(getApplicationContext(),R.layout.testimonylist_row, testimony_list);
        listView.setAdapter(adapter);
    }
}