package com.nuveq.data_entry.common

import android.content.Context
import com.nuveq.data_entry.R
import com.nuveq.data_entry.model.FakeData
import com.nuveq.data_entry.model.HomeSummery
import java.util.*

object FakeDataList {
    private var fakeList = ArrayList<FakeData>()

    open fun getFakeVocDataList(context:Context,homeSummery: HomeSummery): ArrayList<FakeData> {
        fakeList = ArrayList<FakeData>()
        fakeList.add(FakeData(context.resources.getString(R.string.outlate), homeSummery.totalOutLetNumber.toString()))
        fakeList.add(FakeData(context.resources.getString(R.string.target), homeSummery.target.toString()))
        fakeList.add(FakeData(context.resources.getString(R.string.d_target), homeSummery.targetPerDay.toString()))
        fakeList.add(FakeData(context.resources.getString(R.string.working_day), homeSummery.workingDay.toString()))
        fakeList.add(FakeData(context.resources.getString(R.string.till_date), homeSummery.toDateOffTake.toString()))
        fakeList.add(FakeData(context.resources.getString(R.string.off_take), homeSummery.raot.toString()))

        return fakeList
    }
}