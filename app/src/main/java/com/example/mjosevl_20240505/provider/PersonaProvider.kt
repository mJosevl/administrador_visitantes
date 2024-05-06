package com.example.mjosevl_20240505.provider

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import androidx.core.text.isDigitsOnly
import com.example.mjosevl_20240505.database.DatabaseHelper

class PersonaProvider : ContentProvider() {
    companion object {
        const val AUTHORITY = "com.example.mjosevl_20240505.provider"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/visitors")
        private lateinit var databaseHelper: DatabaseHelper
    }

    override fun onCreate(): Boolean {
        databaseHelper = DatabaseHelper(context!!)
        return true  // Return true if the provider was successfully loaded
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return databaseHelper.readableDatabase.query(
            DatabaseHelper.TABLE_VISITORS,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = databaseHelper.writableDatabase.insert(DatabaseHelper.TABLE_VISITORS, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return if (id > 0) Uri.withAppendedPath(CONTENT_URI, id.toString()) else null
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        val count = databaseHelper.writableDatabase.update(
            DatabaseHelper.TABLE_VISITORS,
            values,
            selection,
            selectionArgs
        )
        if (count > 0) context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val count = databaseHelper.writableDatabase.delete(
            DatabaseHelper.TABLE_VISITORS,
            selection,
            selectionArgs
        )
        if (count > 0) context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {

        return when {
            uri.lastPathSegment?.isDigitsOnly() == true -> "vnd.android.cursor.item/vnd.$AUTHORITY.visitor"
            else -> "vnd.android.cursor.dir/vnd.$AUTHORITY.visitors"
        }
    }
}
