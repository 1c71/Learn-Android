package com.example.agoodob.readcontact;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.agoodob.readcontact.R;

/**
 * 2016-3-1
 * 读取联系人
 *
 * 整体概念:
 *  data/data/com.android.provider.contacts/database/contacts2.db
 *  里面存的是联系人的信息
 *
 * 读取联系人整体流程:
 * 1 首先读取联系人需要权限
 * 2 写个函数去读联系人, 先以 HashMap 存，然后做个 ArrayList 存 HashMap
 * 3 set list adapter
 *
 */
public class MainActivity extends Activity {

    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList = (ListView) findViewById(R.id.listView);

        ArrayList<HashMap<String, String>> readContact = readContact();
        // System.out.println(readContact);
        lvList.setAdapter(new SimpleAdapter(this, readContact,
                R.layout.contact_list_item, new String[] { "name", "phone" },
                new int[] { R.id.tv_name, R.id.tv_phone }));
    }

    private ArrayList<HashMap<String, String>> readContact() {
        // 首先,从raw_contacts中读取联系人的id("contact_id")
        // 其次, 根据contact_id从data表中查询出相应的电话号码和联系人名称
        // 然后,根据mimetype来区分哪个是联系人,哪个是电话号码
        Uri rawContactsUri = Uri
                .parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        // 从raw_contacts中读取联系人的id("contact_id")
        Cursor rawContactsCursor = getContentResolver().query(rawContactsUri,
                new String[] { "contact_id" }, null, null, null);
        if (rawContactsCursor != null) {
            while (rawContactsCursor.moveToNext()) {
                String contactId = rawContactsCursor.getString(0);
                // System.out.println(contactId);

                // 根据contact_id从data表中查询出相应的电话号码和联系人名称, 实际上查询的是视图view_data
                Cursor dataCursor = getContentResolver().query(dataUri,
                        new String[] { "data1", "mimetype" }, "contact_id=?",
                        new String[] { contactId }, null);

                if (dataCursor != null) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    while (dataCursor.moveToNext()) {
                        String data1 = dataCursor.getString(0);
                        String mimetype = dataCursor.getString(1);
                        // System.out.println(contactId + ";" + data1 + ";"
                        // + mimetype);
                        if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                            map.put("phone", data1);
                        } else if ("vnd.android.cursor.item/name"
                                .equals(mimetype)) {
                            map.put("name", data1);
                        }
                    }

                    list.add(map);
                    dataCursor.close();
                }
            }

            rawContactsCursor.close();
        }

        return list;
    }

}
