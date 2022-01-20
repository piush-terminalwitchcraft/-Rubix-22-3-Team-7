package com.krishana.androidhackathontemplates.dao

import com.google.firebase.firestore.FirebaseFirestore

class itemsDao {
    val db = FirebaseFirestore.getInstance()
    val itemlist = db.collection("yash")
    .whereLessThan("expiryDate", 4)
    .whereGreaterThan("expiryDate",0)
    .get()
}