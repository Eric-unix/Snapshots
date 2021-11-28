package com.example.snapshots

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Snapshot(var id: String ="",
                    var title: String ="",
                    var photoUrl:String="",
                    var likelist: Map<String, Boolean> = mutableMapOf())
