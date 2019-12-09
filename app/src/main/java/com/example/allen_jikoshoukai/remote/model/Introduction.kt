package com.example.allen_jikoshoukai.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class IntroductionRes(
    val Language: List<Language>
): Parcelable

@Parcelize
data class Language(
    val Background: List<Background>,
    val Education: List<Education>,
    val Hobbies: List<Hobby>,
    val Interests: List<Interest>,
    val Language: String,
    val Name: String,
    val SelfIntroduction: String,
    val Skills: List<Skill>
): Parcelable

@Parcelize
data class Background(
    val Describe: String,
    val EndTime: String,
    val Name: String,
    val Priority: Int,
    val StartTime: String
): Parcelable

@Parcelize
data class Education(
    val Describe: String,
    val EndTime: String,
    val Name: String,
    val Priority: Int,
    val StartTime: String
): Parcelable

@Parcelize
data class Hobby(
    val Describe: String,
    val Level: Int,
    val Name: String
): Parcelable

@Parcelize
data class Interest(
    val Describe: String,
    val Level: Int,
    val Name: String
): Parcelable

@Parcelize
data class Skill(
    val Category: List<Category>,
    val Name: String
): Parcelable

@Parcelize
data class Category(
    val Detail: List<Detail>,
    val Name: String
): Parcelable

@Parcelize
data class Detail(
    val Describe: String,
    val Level: Int,
    val Name: String
): Parcelable