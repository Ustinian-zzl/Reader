package com.bendix.novel.ui.square

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bendix.novel.R
import com.bendix.novel.databinding.FragmentSquareBinding
import com.bendix.novel.model.BookBean
import com.bendix.novel.ui.base.BaseFragment
import com.bendix.novel.ui.novelRead.NovelReadActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SquareFragment: BaseFragment() {

    private lateinit var squareAdapter: SquareAdapter
    private lateinit var binding: FragmentSquareBinding // 声明绑定类变量
    
    @Inject
    lateinit var viewModelFactory: SquareViewModel.AssistedFactory

    val viewModel:SquareViewModel by viewModels {

        SquareViewModel.provideFactory(viewModelFactory)


    }

    override fun getLayoutId(): Int {   
        return R.layout.fragment_square
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSquareBinding.inflate(inflater, container, false)

        Log.d("zzl","SquareFragment onCreateView")
        return binding.root
    
    }
    
    
    override fun initView() {
        Log.d("zzl","SquareFragment initView")
//        binding.refreshLayout.setColorSchemeResources(R.color.colorAccent)
        squareAdapter = SquareAdapter()
        val manager = LinearLayoutManager(activity)
        manager.setOrientation(LinearLayoutManager.VERTICAL);//默认
        squareAdapter.itemClickListener
        binding.rvTypes.apply {
            adapter = squareAdapter
            layoutManager = manager
        }

    }

    override fun initListener() {
        binding.searchTitle.searchEdit.setOnEditorActionListener(object :OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId==EditorInfo.IME_ACTION_SEARCH){
                    Log.d("zzl", v?.text.toString())
                    viewModel.fetchSearchKeyWord(v?.text.toString(),1)

                }
                return false
            }

        })

        viewModel.searchKeyWord.observe(viewLifecycleOwner) {
            Log.d("zzlsearchKeyWord", it.toString())
            fetchPage(it.currentPage, it.totalPage,it.bookBeans)

        }

        viewModel.bookInfo.observe(viewLifecycleOwner) {
            if (it?.bookBean != null) {
                val bookBean = it.bookBean
                viewModel.fetchBook(bookBean)
                NovelReadActivity.startFromActivity(requireActivity(), bookBean)
            }
        }

        squareAdapter.itemClickListener = object : SquareAdapter.OnBookItemClickListener {
            override fun openItem(bookBean: BookBean) {
                Log.d("squareAdapter","点击了")
                viewModel.fetchBookInfo(bookBean.url)
            }
        }
      
        
        

    }

    override fun initData() {
//         val mList: MutableList<BookBean> =ArrayList()
//        Log.d("initData","initData")
//        mList.add(0,BookBean(1,"douluo","",""))
//        mList.add(1,BookBean(1,"zfdsaf","",""))
//        mList.add(2,BookBean(1,"zfdsaf","",""))
//        squareAdapter.addItems(mList)
//        Log.d("initData","addItems")

    }

    
    private fun fetchPage(currentPage:Int,totalPage:Int,bookList:List<BookBean>) {
        if (!bookList.isNullOrEmpty()) {
            if (currentPage == 1) {
                squareAdapter.refreshItems(bookList)
            } else {
                squareAdapter.addItems(bookList)
            }
        }
        viewModel.fetchPage(currentPage + 1, totalPage)
    }

    
}