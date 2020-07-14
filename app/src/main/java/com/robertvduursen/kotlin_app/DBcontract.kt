package com.robertvduursen.kotlin_app

import android.provider.BaseColumns

object DBcontract {
    // Table contents are grouped together in an anonymous object.
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "table_two"
            val COLUMN_ID = "id"
            val COLUMN_FNAME = "fname"
        }


    }
}