package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsContract(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsContractKt(provider) else armsContractJava(provider)

private fun armsContractKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.contractPackageName.value}
${
    when {
        provider.needActivity.value -> {
            "import android.app.Activity"
        }

        provider.needFragment.value -> {
            "import androidx.fragment.app.Fragment"
        }

        else -> ""
    }
}
import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import ${provider.modelPackageName.value}.entity.BaseResponse
import io.reactivex.Observable

$armsAnnotation
interface ${provider.pageName.value}Contract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{

    }
    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel{
//        fun squareTopicHotList(bean: Map<String, Any?>?): Observable<BaseResponse<PaginationEntity<List<SquareTopicMainEntity>>>>
    }
}    
"""

fun armsContractJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.contractPackageName.value};
${
    when {
        provider.needActivity.value -> {
            "import android.app.Activity;"
        }

        provider.needFragment.value -> {
            "import androidx.fragment.app.Fragment;"
        }

        else -> ""
    }
}
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import java.util.Map;
import io.reactivex.Observable;
$armsAnnotation
public interface ${provider.pageName.value}Contract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView{
${
    when {
        provider.needActivity.value -> {
            """
    Activity getActivity();
    """
        }

        provider.needFragment.value -> {
            """
    Fragment getFragment();
    """
        }

        else -> ""
    }
}
    }
    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel{
//            Observable<BaseResponse<PaginationEntity<List<SecretCoinEntity>>>> accountDetail(Map<String, Object> bean);
    }
}    
"""
