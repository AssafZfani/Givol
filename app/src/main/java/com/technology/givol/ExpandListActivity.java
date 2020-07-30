package com.technology.givol;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.technology.givol.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandListActivity extends AppCompatActivity implements View.OnClickListener {
    //     com.technology.givol.adapter.ExpandableListAdapter adapter;
//     ExpandableListView expandableListView;
//    HashMap<String, List<String>> hashMap;
//    ArrayList<String> header;
//    ImageView back_img;
    String activty_no;
    ExpandableListAdapter adapter;
    ImageView back_img;
    String changes_to_tr_co = "We may update our Terms and Conditions from time to time.Thus,you are advised to review this page periodically for any changes.We will notify you of any changes by posting the new Terms and Conditions on this page.These changes are effective immediately after they are posted on this page.";
    String contact_us_des = "If you have any questions or suggestions about our Terms and Conditions,do not hesitate to contact Us at admin@givolapp.com.";
    String contest_id;
    String country_name_expand;
    String eight_des = "At some point,we may wish to update the app.The app is currently available on â€“ the requirements for system(and for any additional systems we decide to extend the availability of the app to) may change,and youâ€™ll need to download the updates if you want to keep using the app.Ego Media does not promise that it will always update the app so that it is relevant to you and/or works with the version that you have installed on your device.However,you promise to always accept updates to the application when offered to you,We may also wish to stop providing the app,and may terminate use of it at any time without giving notice of termination to you.Unless we tell you otherwise,upon any termination,(a)the rights and licenses granted to you in these terms will end;(b)you must stop using the app,and(if needed)delete it from your device.";
    ExpandableListView expandableListView;
    String first_des = "By downloading or using the app,these terms will automatically apply to you â€“ you should make sure therefore that you read them carefully before using the app.Youâ€™re not allowed to copy,or modify the app,any part of the app,or our trademarks in any way.Youâ€™re not allowed to attempt to extract the source code of the app,and you also shouldnâ€™t try to translate the app into other languages,or make derivative versions.The app itself,and all the trade marks,copyright,database rights and other intellectual property rights related to it,still belong to Ego Media.";
    String five_des = "If youâ€™re using the app outside of an area with Wi-Fi,you should remember that your terms of the agreement with your mobile network provider will still apply.As a result,you may be charged by your mobile provider for the cost of data for the duration of the connection while accessing the app,or other third party charges.In using the app,youâ€™re accepting responsibility for any such charges,including roaming data charges if you use the app outside of your home territory(i.e. region or country) without turning off data roaming.If you are not the bill payer for the device on which youâ€™re using the app,please be aware that we assume that you have received permission from the bill payer for using the app.";
    String fouth_des = "You should be aware that there are certain things that Ego Media will not take responsibility for.Certain functions of the app will require the app to have an active internet connection.The connection can be Wi-Fi,or provided by your mobile network provider,but Ego Media cannot take responsibility for the app not working at full functionality if you donâ€™t have access to Wi-Fi,and you donâ€™t have any of your data allowance left.";
    HashMap<String, List<String>> hashMap;
    ArrayList<String> header;
    HashMap<String, List<String>> listDataChild;
    List<String> listDataParent;
    String second_des = "Ego Media is committed to ensuring that the app is as useful and efficient as possible.For that reason,we reserve the right to make changes to the app or to charge for its services,at any time and for any reason.We will never charge you for the app or its services without making it very clear to you exactly what youâ€™re paying for.";
    String seven_des = "With respect to Ego Mediaâ€™s responsibility for your use of the app,when youâ€™re using the app,itâ€™s important to bear in mind that although we endeavour to ensure that it is updated and correct at all times,we do rely on third parties to provide information to us so that we can make it available to you.Ego Media accepts no liability for any loss,direct or indirect,you experience as a result of relying wholly on this functionality of the app.";
    String six_des = "Along the same lines,Ego Media cannot always take responsibility for the way you use the app i.e.You need to make sure that your device stays charged â€“ if it runs out of battery and you canâ€™t turn it on to avail the Service,Ego Media cannot accept responsibility.";
    String third_des = "The Givol app stores and processes personal data that you have provided to us,in order to provide our Service.Itâ€™s your responsibility to keep your phone and access to the app secure.We therefore recommend that you do not jailbreak or root your phone,which is the process of removing software restrictions and limitations imposed by the official operating system of your device.It could make your phone vulnerable to malware/viruses/malicious programs,compromise your phoneâ€™s security features and it could mean that the Givol app wonâ€™t work properly or at all.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list);
//        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);
//        expandableListView.setGroupIndicator(null);
//        setItems();
//        adapter = new ExpandableListAdapter(this, this.header, this.hashMap);
//        expandableListView.setAdapter(adapter);
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                return false;
//            }
//        });
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            public void onGroupExpand(int groupPosition) {
//                Context applicationContext = ExpandListActivity.this.getApplicationContext();
//                StringBuilder sb = new StringBuilder();
//                sb.append((String) ExpandListActivity.this.header.get(groupPosition));
//                sb.append(" Expanded");
//                Toast.makeText(applicationContext, sb.toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            public void onGroupCollapse(int groupPosition) {
//                Context applicationContext = ExpandListActivity.this.getApplicationContext();
//                StringBuilder sb = new StringBuilder();
//                sb.append((String) ExpandListActivity.this.header.get(groupPosition));
//                sb.append(" Collapsed");
//                Toast.makeText(applicationContext, sb.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Context applicationContext = ExpandListActivity.this.getApplicationContext();
//                StringBuilder sb = new StringBuilder();
//                sb.append((String) ExpandListActivity.this.header.get(groupPosition));
//                sb.append(" : ");
//                sb.append((String) ((List) ExpandListActivity.this.hashMap.get(ExpandListActivity.this.header.get(groupPosition))).get(childPosition));
//                Toast.makeText(applicationContext, sb.toString(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        final int[] prevExpandPosition = {-1};
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
//                    expandableListView.collapseGroup(prevExpandPosition[0]);
//                }
//                prevExpandPosition[0] = groupPosition;
//            }
//        });
//
//        back_img=(ImageView)findViewById(R.id.back_img);
//        back_img.setOnClickListener(this);
//    }
//
//    /* access modifiers changed from: 0000 */
//    public void setItems() {
//        String str;
//        this.header = new ArrayList<>();
//        String str2 = "Contest Formats";
//        this.header.add(str2);
//        this.header.add("Contest Prizes");
//        this.header.add("Registration");
//        this.header.add("Eligibility");
//        this.header.add("Contest Plan");
//        this.header.add("Selection & Verification");
//        this.header.add("Condition relating to prizes");
//        List<String> child1 = new ArrayList<>();
//        List<String> child2 = new ArrayList<>();
//        List<String> child3 = new ArrayList<>();
//        List<String> child4 = new ArrayList<>();
//        List<String> child5 = new ArrayList<>();
//        List<String> child6 = new ArrayList<>();
//        List<String> child7 = new ArrayList<>();
//
//
//
//        this.hashMap = new HashMap<>();
//        int i = 1;
//        while (true) {
//            str = "Child";
//            if (i >= 5) {
//                break;
//            }
//            StringBuilder sb = new StringBuilder();
//            sb.append(str);
//            sb.append(i);
//            child1.add(sb.toString());
//            i++;
//        }
//        for (int i2 = 1; i2 < 5; i2++) {
//            StringBuilder sb2 = new StringBuilder();
//            sb2.append(str);
//            sb2.append(i2);
//            child2.add(sb2.toString());
//        }
//        for (int i3 = 1; i3 < 6; i3++) {
//            StringBuilder sb3 = new StringBuilder();
//            sb3.append(str);
//            sb3.append(i3);
//            child3.add(sb3.toString());
//        }
//        for (int i4 = 1; i4 < 7; i4++) {
//            StringBuilder sb4 = new StringBuilder();
//            sb4.append(str);
//            sb4.append(i4);
//            child4.add(sb4.toString());
//        }
//        for (int i5 = 1; i5 < 5; i5++) {
//            StringBuilder sb5 = new StringBuilder();
//            sb5.append(str);
//            sb5.append(i5);
//            child5.add(sb5.toString());
//        }
//        for (int i6 = 1; i6 < 5; i6++) {
//            StringBuilder sb6 = new StringBuilder();
//            sb6.append(str);
//            sb6.append(i6);
//            child6.add(sb6.toString());
//        }
//        for (int i7 = 1; i7 < 5; i7++) {
//            StringBuilder sb7 = new StringBuilder();
//            sb7.append(str);
//            sb7.append(i7);
//            child7.add(sb7.toString());
//        }
//        this.hashMap.put(this.header.get(0), child1);
//        this.hashMap.put(this.header.get(1), child2);
//        this.hashMap.put(this.header.get(2), child3);
//        this.hashMap.put(this.header.get(3), child4);
//        this.hashMap.put(this.header.get(4), child5);
//        this.hashMap.put(this.header.get(5), child6);
//        this.hashMap.put(this.header.get(6), child7);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.back_img:
//                startActivity(new Intent(getApplicationContext(), NavActivity.class));
//                break;
//        }
//
//    }
//}
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.country_name_expand = bundle.getString("COUNTRY_NAME");
            this.contest_id = bundle.getString("CATEGORY_ID");
            this.activty_no = bundle.getString("ACTIVITY_NO");
        }
        this.expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);
        this.expandableListView.setGroupIndicator(null);
        createListData();
        this.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        this.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                Context applicationContext = ExpandListActivity.this.getApplicationContext();
                StringBuilder sb = new StringBuilder();
                sb.append((String) ExpandListActivity.this.listDataParent.get(groupPosition));
                sb.append(" Expanded");
            }
        });
        this.expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            public void onGroupCollapse(int groupPosition) {
                Context applicationContext = ExpandListActivity.this.getApplicationContext();
                new StringBuilder().append((String) ExpandListActivity.this.listDataParent.get(groupPosition));
            }
        });
        this.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Context applicationContext = ExpandListActivity.this.getApplicationContext();
                new StringBuilder();
                return false;
            }
        });
        final int[] prevExpandPosition = {-1};
        this.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                int[] iArr = prevExpandPosition;
                if (iArr[0] >= 0 && iArr[0] != groupPosition) {
                    ExpandListActivity.this.expandableListView.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0] = groupPosition;
            }
        });
        this.back_img = (ImageView) findViewById(R.id.back_img);
        this.back_img.setOnClickListener(this);
    }

    public void setItems() {
        int i;
        String first_des2 = "By downloading or using the app,these terms will automatically apply to you â€“ you should make sure therefore that you read them carefully before using the app.Youâ€™re not allowed to copy,or modify the app,any part of the app, or our trademarks in any way.Youâ€™re not allowed to attempt to extract the source code of the app,and you also shouldnâ€™t try to translate the app into other languages, or make derivative versions. The app itself,and all the trade marks, copyright, database rights and other intellectual property rights related to it,still belong to Ego Media.";
        String second_des2 = "Ego Media is committed to ensuring that the app is as useful and efficient as possible.For that reason,we reserve the right to make changes to the app or to charge for its services,at any time and for any reason.We will never charge you for the app or its services without making it very clear to you exactly what youâ€™re paying for.";
        String third_des2 = "The Givol app stores and processes personal data that you have provided to us, in order to provide our Service. Itâ€™s your responsibility to keep your phone and access to the app secure. We therefore recommend that you do not jailbreak or root your phone, which is the process of removing software restrictions and limitations imposed by the official operating system of your device. It could make your phone vulnerable to malware/viruses/malicious programs, compromise your phoneâ€™s security features and it could mean that the Givol app wonâ€™t work properly or at all.";
        String fouth_des2 = "You should be aware that there are certain things that Ego Media will not take responsibility for.Certain functions of the app will require the app to have an active internet connection.The connection can be Wi-Fi, or provided by your mobile network provider,but Ego Media cannot take responsibility for the app not working at full functionality if you donâ€™t have access to Wi-Fi,and you donâ€™t have any of your data allowance left.";
        String five_des2 = "If youâ€™re using the app outside of an area with Wi-Fi, you should remember that your terms of the agreement with your mobile network provider will still apply.As a result,you may be charged by your mobile provider for the cost of data for the duration of the connection while accessing the app, or other third party charges. In using the app, youâ€™re accepting responsibility for any such charges,including roaming data charges if you use the app outside of your home territory (i.e. region or country) without turning off data roaming.If you are not the bill payer for the device on which youâ€™re using the app,please be aware that we assume that you have received permission from the bill payer for using the app.";
        String six_des2 = "Along the same lines,Ego Media cannot always take responsibility for the way you use the app i.e.You need to make sure that your device stays charged â€“ if it runs out of battery and you canâ€™t turn it on to avail the Service,Ego Media cannot accept responsibility.";
        String seven_des2 = "With respect to Ego Mediaâ€™s responsibility for your use of the app, when youâ€™re using the app,itâ€™s important to bear in mind that although we endeavour to ensure that it is updated and correct at all times,we do rely on third parties to provide information to us so that we can make it available to you.Ego Media accepts no liability for any loss, direct or indirect,you experience as a result of relying wholly on this functionality of the app.";
        String eight_des2 = "At some point, we may wish to update the app. The app is currently available on â€“ the requirements for system (and for any additional systems we decide to extend the availability of the app to) may change, and youâ€™ll need to download the updates if you want to keep using the app. Ego Media does not promise that it will always update the app so that it is relevant to you and/or works with the version that you have installed on your device. However,you promise to always accept updates to the application when offered to you,We may also wish to stop providing the app,and may terminate use of it at any time without giving notice of termination to you.Unless we tell you otherwise, upon any termination, (a) the rights and licenses granted to you in these terms will end; (b) you must stop using the app, and (if needed) delete it from your device.";
        String changes_to_tr_co2 = "We may update our Terms and Conditions from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Terms and Conditions on this page. These changes are effective immediately after they are posted on this page.";
        String contact_us_des2 = "If you have any questions or suggestions about our Terms and Conditions,do not hesitate to contact Us at admin@givolapp.com.";
        this.header = new ArrayList<>();
        String str2 = "Terms & Conditions";
        this.header.add(str2);
        this.header.add("Changes to This Terms and Conditions");
        this.header.add("Contact Us");
        List<String> child1 = new ArrayList<>();
        List<String> child2 = new ArrayList<>();
        List<String> child3 = new ArrayList<>();
        this.hashMap = new HashMap<>();
        String str = "Child";
        int i2 = 1;
        while (true) {
            String str22 = str2;
            if (i2 >= 2) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(first_des2);
            String first_des3 = first_des2;
            String first_des4 = "\n";
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(second_des2);
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(third_des2);
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(fouth_des2);
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(five_des2);
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(six_des2);
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(seven_des2);
            sb.append(first_des4);
            sb.append(first_des4);
            sb.append(eight_des2);
            sb.append(i2);
            child1.add(sb.toString());
            i2++;
            str2 = str22;
            first_des2 = first_des3;
        }
        int i22 = 1;
        for (i = 2; i22 < i; i = 2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(changes_to_tr_co2);
            String second_des3 = second_des2;
            child2.add(sb2.toString());
            i22++;
            second_des2 = second_des3;
        }
        for (int i3 = 1; i3 < 2; i3++) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(contact_us_des2);
            child3.add(sb3.toString());
        }
        this.hashMap.put(this.header.get(0), child1);
        this.hashMap.put(this.header.get(1), child2);
        this.hashMap.put(this.header.get(2), child3);
    }

    private void createListData() {
        this.listDataParent = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataParent.add("Terms & Conditions");
        this.listDataParent.add("Changes to This Terms and Conditions");
        this.listDataParent.add("Contact Us");
        List<String> description = new ArrayList<>();
        description.add(this.first_des);
        description.add(this.second_des);
        description.add(this.third_des);
        description.add(this.fouth_des);
        description.add(this.five_des);
        description.add(this.six_des);
        description.add(this.seven_des);
        description.add(this.eight_des);
        List<String> change_description = new ArrayList<>();
        change_description.add(this.changes_to_tr_co);
        List<String> contact_us = new ArrayList<>();
        contact_us.add(this.contact_us_des);
        this.listDataChild.put(this.listDataParent.get(0), description);
        this.listDataChild.put(this.listDataParent.get(1), change_description);
        this.listDataChild.put(this.listDataParent.get(2), contact_us);
        this.adapter = new ExpandableListAdapter(this, this.listDataParent, this.listDataChild);
        this.expandableListView.setAdapter(this.adapter);
    }

    public void onClick(View v) {
//        if (v.getId() == R.id.back_img) {
//            String str = "COUNTRY_NAME";
//            if (this.activty_no.equalsIgnoreCase("2")) {
//                Intent intent = new Intent(getApplicationContext(), NavItemDetailActivity.class);
//                intent.putExtra(str, this.country_name_expand);
//                intent.putExtra("CATEGORY_ID", this.contest_id);
//                startActivity(intent);
//                finish();
//            } else if (this.activty_no.equalsIgnoreCase("1")) {
//                Intent intent2 = new Intent(getApplicationContext(), NavActivity.class);
//                intent2.putExtra(str, this.country_name_expand);
//                startActivity(intent2);
//                finish();
//            }
//        }
    }
}
