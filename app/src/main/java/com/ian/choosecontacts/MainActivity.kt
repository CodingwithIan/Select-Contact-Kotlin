package com.ian.choosecontacts

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore.Video.query
import android.widget.EditText
import androidx.core.content.ContentResolverCompat.query


class MainActivity : AppCompatActivity() {
    var phone:EditText ?=null
    var name:EditText ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phone=findViewById(R.id.editTextPhone)
        name=findViewById(R.id.editTextTextPersonName)

        phone!!.setOnClickListener {
            var i=Intent(Intent.ACTION_PICK)
            i.type=ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i,100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==100 && resultCode== Activity.RESULT_OK){
                var contactUri=data?.data?:return
                var columns= arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                var cursor=contentResolver.query(contactUri,columns,null,null,null)

                if (cursor!!.moveToFirst()!!){
                    phone!!.setText(cursor.getString(0))
                    name!!.setText(cursor.getString(1))

                }
            }
    }
}