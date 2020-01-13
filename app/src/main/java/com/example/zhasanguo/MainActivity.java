package com.example.zhasanguo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.zhasanguo.person.Person;
import com.example.zhasanguo.person.PersonAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Person> personList = new ArrayList<>();
    private PersonAdapter adapter;
    private Context context;
    private List<Drawable> mImageables;
    private int cur;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPersons();
        iv = findViewById(R.id.person_image1);
        RecyclerView recyclerView = findViewById(R.id.ccc);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);



    }
    private  void initPersons() {

        //--------------------------------------------------------------------
        //Debug:
       XmlResourceParser parser = getResources().getXml(R.xml.array);
        //TypedArray parser = getResources().obtainTypedArray(R.array.pic);
        try {
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){    //如果还没到文档的结束标志，那么就继续往下处理
                switch (event){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        Log.d("@_@", parser.getName());
                        if (parser.getName().equals("item")){
                            /*
                            String str = parser.getAttributeName(0) + ":" + parser.getAttributeValue(0);
                            Log.d("@_@", str);

                            str = parser.getAttributeName(1) + ":" + parser.getAttributeValue(1);
                            Log.d("@_@", str);

                            str = parser.getAttributeName(2) + ":" + parser.getAttributeValue(2);
                            Log.d("@_@", str);
                             */
        //String strID = parser.getAttributeValue(null, "id");
        //int nID = Integer.parseInt(strID);
                      int nID= Integer.parseInt(parser.getAttributeValue(null, "id"));
                            String strImageName = parser.getAttributeValue(null, "image_name");
                            String introduction = parser.getAttributeValue(null, "introduction");
                            Person obj = new Person(nID, strImageName, introduction);
                            personList.add(obj);

                        }

                        break;
                    case XmlPullParser.TEXT:

                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                event = parser.next();   //将当前解析器光标往下一步移
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //----------------------------------------------------------------------------
        personList.size();

        int nImageID = getDrawableId(getApplicationContext(),  "aaa.png");
        getApplicationContext().getResources();
        //ImageView personImageView = findViewById(nImageID);
        Log.d("@_@", "test");

        /*
        TypedArray typedArray = getResources().obtainTypedArray(R.array.persons_image);
        String[] titleArr = getResources().getStringArray(R.array.persons);
        if (null != titleArr) {
            int titleLength = titleArr.length;
            for (int index = 0; index < titleLength; index++) {
                int feedResId = typedArray.getResourceId(index, 0);
                Person person = new Person(feedResId);
                personList.add(person);
            }
        }
        */

    }
    public static int getDrawableId(Context paramContext, String paramString)
    {
        return paramContext.getResources().getIdentifier(paramString,
                "drawable", paramContext.getPackageName());
    }
}
