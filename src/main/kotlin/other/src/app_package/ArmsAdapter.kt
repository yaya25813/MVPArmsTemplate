package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation
import other.commonAnnotation

fun armsAdapter(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsAdapterKt(provider) else armsAdapterJava(provider)

private fun armsAdapterKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.adapterPackageName.value}
import android.content.Context
import android.view.ViewGroup  
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import ${provider.appPackageName.value}.R 
import  ${provider.entityPackageName.value}.${provider.pageName.value}Entity;
class ${provider.pageName.value}Adapter() :
    BaseQuickAdapter<${provider.pageName.value}Entity, QuickViewHolder>() {
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.${provider.adapterLayoutName.value}, parent)
    }

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: ${provider.pageName.value}Entity?) {
//        ArmsUtils.obtainAppComponentFromContext(context)
//                        .imageLoader()
//                        .loadImage(context, ImageConfigImpl
//                                .builder()
//                                .placeholder(R.mipmap.ic_launcher_round) //占位照片 头像icon_user_avatar  占位icon_app_null
//                                .fallback(R.mipmap.ic_launcher_round) //异常照片
//                                .imageRadius(8)  //图片圆角 isCircle(true) 圆图
//                                .url(Api.DEBUG_IMG) // 图片地址
//                                .imageView(helper.getView(R.id.iv_shop_wy_recommend)).build())
    }
}

"""

fun armsAdapterJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.adapterPackageName.value};
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ${provider.appPackageName.value}.R;
import  ${provider.entityPackageName.value}.${provider.pageName.value}Entity;
public class ${provider.pageName.value}Adapter extends BaseQuickAdapter <${provider.pageName.value}Entity, QuickViewHolder> {

   @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int position) {
        return new QuickViewHolder(R.layout.${provider.adapterLayoutName.value}, parent);
    }
    
   @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int position, @Nullable ${provider.pageName.value}Entity item) {
//        ArmsUtils.obtainAppComponentFromContext(getContext())
//                        .imageLoader()
//                        .loadImage(getContext(), ImageConfigImpl
//                                .builder()
//                                .placeholder(R.mipmap.ic_launcher_round) //占位照片 头像icon_user_avatar  占位icon_app_null
//                                .fallback(R.mipmap.ic_launcher_round) //异常照片
//                                .imageRadius(8)  //图片圆角 isCircle(true) 圆图
//                                .url(Api.DEBUG_IMG) // 图片地址
//                                .imageView(helper.getView(R.id.iv_shop_wy_recommend)).build());
    }
}   
"""
