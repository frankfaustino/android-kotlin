//package com.example.android.devbyteviewer.work
//
//import android.content.Context
//import androidx.work.CoroutineWorker
//import androidx.work.WorkerParameters
//import retrofit2.HttpException
//
//class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
//    override suspend fun doWork(): Result {
//        val database = getDatabase(applicationContext)
//        val repository = VideosRepositry(database)
//
//        try {
//            repository.refreshVideos()
//        } catch (e: HttpException) {
//            return Result.retry()
//        }
//
//        return Result.success()
//    }
//}