package com.kidslearning.kidsplay.smartkids.derdroid.preschool.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.customclasses.Constant
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.model.ModelVideo
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by Naynesh Patel on 12-Feb-19.
 */
class DatabaseHelper(internal var context: Context) : SQLiteOpenHelper(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION) {

    val videoDetails: ArrayList<ModelVideo>
        get() {
            var arrPfVideoDetails = ArrayList<ModelVideo>()
            val db = this.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM kids WHERE id=" + Constant.VIDEO_CATEGORY_ID, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                var modelVideo = ModelVideo()
                var videoDescription = cursor.getString(cursor.getColumnIndexOrThrow(Constant.VIDEO))
                modelVideo.videoId = videoDescription.split("#")[0]
                modelVideo.videoTitle = videoDescription.split("#")[1]
                modelVideo.videoThumb = "https://i3.ytimg.com/vi/" + videoDescription.split("#")[0]+"/hqdefault.jpg"
                arrPfVideoDetails.add(modelVideo)
                cursor.moveToNext()
            }
            return arrPfVideoDetails
        }

    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    @Throws(IOException::class)
    fun createDataBase() {
        val databaseExist = checkDataBase()
        if (databaseExist) {
        } else {
            this.writableDatabase
            copyDataBase()
        }
    }

    private fun checkDataBase(): Boolean {
        val databaseFile = File(Constant.DB_PATH + Constant.DATABASE_NAME)
        return databaseFile.exists()
    }

    @Throws(IOException::class)
    private fun copyDataBase() {
        val myInput = context.assets.open("databases/" + Constant.DATABASE_NAME)
        val outFileName = Constant.DB_PATH + Constant.DATABASE_NAME
        val myOutput = FileOutputStream(outFileName)
        val buffer = ByteArray(1024)
        while (myInput.read(buffer) > 0) {
            myOutput.write(buffer)
        }
        myOutput.flush()
        myOutput.close()
        myInput.close()
    }

}
