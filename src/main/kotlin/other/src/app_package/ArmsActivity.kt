package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation
import java.util.*

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding
import ${provider.appPackageName.value}.mvp.ui.ModuleConfig
import zlc.season.butterfly.annotation.Destination

${commonAnnotation(provider)}
@Destination(route = ModuleConfig.App.${provider.pageName.value.uppercase(Locale.getDefault())})
class ${provider.pageName.value}Activity : BaseActivity<${provider.pageName.value}Presenter, Activity${provider.pageName.value}Binding>() , ${provider.pageName.value}Contract.View, View.OnClickListener {
    override fun setupActivityComponent(appComponent: AppComponent) {
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
//         setTopTitle("")
        
    }
    
    override fun initData() {
 
        initListener()
    }
    
    private fun initListener() {
//        onForClickListener(this, )
    }
    
    override fun onClick(view: View) {
      when (view) {
//            binding?. -> {
//            }
        }
    }
    
    override fun getActivity(): Activity = this
    
    override fun onPause() {
        super.onPause()
        hideLoading()
    }
    
    override fun createBinding(): Activity${provider.pageName.value}Binding {
        return Activity${provider.pageName.value}Binding.inflate(LayoutInflater.from(this))
    }
    
    override fun showMessage(message: String) {
        if (DataTool.isNotEmpty(message)) {
            ArmsUtils.snackbarText(message)
        }
    }

    override fun killMyself() {
        finish()
    }
}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};
import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.base.BaseActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.jess.arms.utils.ArmsUtils;
import android.content.Intent;
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;

${commonAnnotation(provider)}
@Destination(route = ModuleConfig.App.${provider.pageName.value})
public class ${provider.pageName.value}Activity extends BaseActivity<${provider.pageName.value}Presenter, Activity${provider.pageName.value}Binding> implements ${provider.pageName.value}Contract.View, View.OnClickListener{
 
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    @Override
    public void initView(){
        //setTopTitle("");
 
//            onForClickListener(this,getBinding());
    }
    
    @Override
    public void initData() {
        
        initListener();
    }
    
    public void initListener() {
    
    }
    
    @Override
    public void onClick(View view) {
        
    }
    
    public Activity getActivity(){
        return this;
    }
    
    @Override
    protected Activity${provider.pageName.value}Binding createBinding() {
        return Activity${provider.pageName.value}Binding.inflate(LayoutInflater.from(this));
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        hideLoading();
    }
    
    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.snackbarText(message);
    }
 

    @Override
    public void killMyself() {
        finish();
    }
}
"""
