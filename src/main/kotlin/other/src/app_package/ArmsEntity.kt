package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsEntity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsEntityKt(provider) else armsEntityJava(provider)




fun armsEntityKt(provider: ArmsPluginTemplateProviderImpl) = """
    
package ${provider.entityPackageName.value};


class ${provider.pageName.value}Entity : BaseResponse<Any?>(){
   
}   
"""


fun armsEntityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.entityPackageName.value};
import org.jetbrains.annotations.NotNull;

public class ${provider.pageName.value}Entity extends BaseResponse{
   
}   
"""
