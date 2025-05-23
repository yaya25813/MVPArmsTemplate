package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsModel(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsModelKt(provider) else armsModelJava(provider)

private fun armsModelKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.modelPackageName.value}
import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import ${provider.modelPackageName.value}.entity.BaseResponse
import io.reactivex.Observable
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
    
//    override fun listParkingNames(bean: Map<String, Any?>?): Observable<BaseResponse<PaginationEntity<List<SquareTopicMainEntity>>>> {
//        return mRepositoryManager.obtainRetrofitService(DeviceService::class.java)
//            .listParkingNames(bean)
//    }
}   
"""


fun armsModelJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.modelPackageName.value};
import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
${
    if (provider.needActivity.value && provider.needFragment.value)
        "import com.jess.arms.di.scope.ActivityScope;"
    else if (provider.needActivity.value)
        "import com.jess.arms.di.scope.ActivityScope;"
    else if (provider.needFragment.value)
        "import com.jess.arms.di.scope.FragmentScope;"
    else ""
}
import javax.inject.Inject;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import java.util.Map;
import io.reactivex.Observable;
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
public class ${provider.pageName.value}Model extends BaseModel implements ${provider.pageName.value}Contract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ${provider.pageName.value}Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
    
//    @Override
//    public Observable<BaseResponse<PaginationEntity<List<SecretCoinEntity>>>> accountDetail(Map<String, Object> bean) {
//        return mRepositoryManager.obtainRetrofitService(DeviceService.class)
//                .accountDetail(bean);
//    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}   
"""
