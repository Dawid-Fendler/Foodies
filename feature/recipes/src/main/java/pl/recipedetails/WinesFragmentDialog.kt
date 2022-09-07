package pl.recipedetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import pl.architecture.base.BaseDialogFragment
import pl.recipes.databinding.DialogFragmentWinesBinding

class WinesFragmentDialog :
    BaseDialogFragment<DialogFragmentWinesBinding>(DialogFragmentWinesBinding::inflate) {

    private val arguments: WinesFragmentDialogArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initWineView()
        initCancelIconOnClick()
    }

    private fun initWineView() {
        arguments.wines.wines.forEach { wineName ->
            binding.winesContainer.addView(WineView(requireContext()).apply { setWineName(wineName) })
        }
    }

    private fun initCancelIconOnClick() {
        binding.cancelIcon.setOnClickListener { dismiss() }
    }
}