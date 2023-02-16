package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsAdapter(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsAdapter(provider) else armsAdapterJava(provider)

private fun armsAdapter(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.adapterPackageName.value}
import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
${
    if (provider.needActivity.value && provider.needFragment.value)
        "import com.jess.arms.di.scope.ActivityScope"
    else if (provider.needActivity.value)
        "import com.jess.arms.di.scope.ActivityScope"
    else if (provider.needFragment.value)
        "import com.jess.arms.di.scope.FragmentScope"
    else ""
}
import javax.inject.Inject
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract

$armsAnnotation
${
    if (provider.needActivity.value && provider.needFragment.value)
        "@ActivityScope"
    else if (provider.needActivity.value)
        "@ActivityScope"
    else if (provider.needFragment.value)
        "@FragmentScope"
    else ""
}
class ${provider.pageName.value}Model
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), ${provider.pageName.value}Contract.Model{
    @Inject
    lateinit var mGson:Gson
    @Inject
    lateinit var mApplication: Application

    override fun onDestroy() {
          super.onDestroy()
    }
}   
"""


fun armsAdapterJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.adapterPackageName.value};
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import android.content.Context;
import ${provider.appPackageName.value}.R;
import  ${provider.entityPackageName.value}.${provider.pageName.value}Entity;
public class ${provider.pageName.value}Adapter extends BaseQuickAdapter <${provider.pageName.value}Entity, BaseViewHolder> {
    private Context getContext;

    public ${provider.pageName.value}Adapter(Context context) {
        super(R.layout.${provider.adapterLayoutName.value});
        getContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper,${provider.pageName.value}Entity item) {
        
        
//   new GlideImageLoaderStrategy().loadImage(getContext,
//                    ImageConfigImpl.builder()
//                            .errorPic(R.drawable.icon_parking_mobile_img)  //异常照片
//                            .imageRadius(8)  //图片圆角
//                            .url(item.getImage()) // 图片河南省郑州市金水区西史赵 
//                            .imageView(helper.getView(R.id.iv_parking_mobile_item_img)).build());
    }
}   
"""