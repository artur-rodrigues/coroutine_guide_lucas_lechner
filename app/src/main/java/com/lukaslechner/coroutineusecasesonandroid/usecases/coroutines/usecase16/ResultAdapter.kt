package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase16

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lukaslechner.coroutineusecasesonandroid.R
import com.lukaslechner.coroutineusecasesonandroid.databinding.RecyclerviewItemCalculationResultBinding

class ResultAdapter(
    private val results: MutableList<UiState.Success> = mutableListOf()
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    class ViewHolder(
        val layout: LinearLayout,
        val factorialOf: TextView,
        val numberOfCoroutines: TextView,
        val dispatcher: TextView,
        val yielding: TextView,
        val calculationDuration: TextView,
        val stringConversionDuration: TextView,
        val computationResult: TextView

    ) :
        RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            RecyclerviewItemCalculationResultBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(
            binding.root,
            binding.textViewResultFactorialOf,
            binding.textViewResultNumberCoroutines,
            binding.textViewResultDispatcher,
            binding.textViewResultYield,
            binding.textViewDuration,
            binding.textViewStringConversionDuration,
            binding.textViewResult
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addResult(state: UiState.Success) {
        results.add(0, state)
        notifyDataSetChanged()
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        val result = results[position]
        val context = layout.context

        factorialOf.text = context.getString(R.string.calculate_factorial, result.factorialOf)
        numberOfCoroutines.text = context.getString(R.string.coroutine, result.numberOfCoroutines)
        dispatcher.text = context.getString(R.string.dispatcher_info, result.dispatcherName)
        yielding.text = context.getString(R.string.yield, result.yieldDuringCalculation)

        calculationDuration.text =
            context.getString(R.string.duration_calculation, result.computationDuration)

        stringConversionDuration.text =
            context.getString(R.string.duration_string_conversion, result.stringConversionDuration)

        computationResult.text = if (result.result.length <= 150) {
            "Result: ${result.result}"
        } else {
            "Result: ${result.result.substring(0, 147)}..."
        }
    }
}