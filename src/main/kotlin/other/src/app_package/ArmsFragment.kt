package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsFragmentKt(provider) else armsFragmentJava(provider)

private fun armsFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import ${provider.appPackageName.value}.databinding.Fragment${provider.pageName.value}Binding;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment : BaseFragment<${provider.pageName.value}Presenter, Fragment${provider.pageName.value}Binding>() , ${provider.pageName.value}Contract.View{
    private var getText: String? = null

    companion object {
        //用于接受数据
        private const val TYPE = "TYPE"
        fun newInstance(type: String?): ${provider.pageName.value}Fragment {
            val fragment = ${provider.pageName.value}Fragment()
            val bundle = Bundle()
            bundle.putString(TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun setupFragmentComponent(appComponent:AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].lowercaseChar()}${
    provider.pageName.value.substring(
        1,
        provider.pageName.value.length
    )
}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
   override fun initView() {
   
   }
    
    /**
     * 在 onActivityCreate()时调用
     */
    override fun initData(savedInstanceState: Bundle?) {
        
        initListener()
    }
    override fun setData(data: Any?) {}
    
    private fun initListener() {
    
    }
    
    val fragment: Fragment
        get() = this
    
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): Fragment${provider.pageName.value}Binding {
        return Fragment${provider.pageName.value}Binding.inflate(inflater, container, false)
    }
    
    override fun onPause() {
        super.onPause()
        hideLoading()
    }

    override fun getBundle(bundle: Bundle) {
        assert(arguments != null)
        getText = requireArguments().getString(TYPE)
    }

    override fun showMessage(message: String) {
        if (DataTool.isNotEmpty(message)) {
            ArmsUtils.snackbarText(message)
        }
    }

}
    
"""


fun armsFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;
import android.content.Intent;
${commonAnnotation(provider)}
public class ${provider.pageName.value}Fragment extends BaseFragment<${provider.pageName.value}Presenter, Fragment${provider.pageName.value}Binding> implements ${provider.pageName.value}Contract.View{
     //用于接受数据
     private static String TYPE = "TYPE";
     private String getText; 
//     View  ;
    public static ${provider.pageName.value}Fragment newInstance(String type) {
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    @Override
    public void initView() {
//            preview_view = getBinding().previewView;
//            onForClickListener(this,getBinding());    
    }
    
    /**
     * 在 onActivityCreate()时调用
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {        
        initListener();
    }
    
    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link //Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    private void initListener() {
   
	}
    
    public Fragment getFragment(){
        return this;
    }
    
    @Override
    protected Fragment${provider.pageName.value}Binding createBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return Fragment${provider.pageName.value}Binding.inflate(inflater, container, false);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        hideLoading();
    }
    @Override
    public void getBundle(Bundle bundle) {
        assert getArguments() != null;
        getText = getArguments().getString(TYPE);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.snackbarText(message);
    }
}
    
"""
