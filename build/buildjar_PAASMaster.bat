cd %JDEV_USER_HOME_11119%\mywork\PaaS\build

%MW_HOME_11119%\jdeveloper\jdev\bin\ojdeploy -profile adflibPAASCommonSDK -workspace PAASCommon.jws -project PAASCommonSDK -basedir %JDEV_USER_HOME_11119%\mywork\PaaS\PAASCommon -clean > buildjar_PAAS.log 2>&1
%MW_HOME_11119%\jdeveloper\jdev\bin\ojdeploy -profile adflibPAASCommonModel -workspace PAASCommon.jws -project PAASCommonModel -basedir %JDEV_USER_HOME_11119%\mywork\PaaS\PAASCommon -clean > buildjar_PAAS.log 2>&1
%MW_HOME_11119%\jdeveloper\jdev\bin\ojdeploy -profile adflibPAASCommonViewController -workspace PAASCommon.jws -project PAASCommonViewController -basedir %JDEV_USER_HOME_11119%\mywork\PaaS\PAASCommon -clean > buildjar_PAAS.log 2>&1

%MW_HOME_11119%\jdeveloper\jdev\bin\ojdeploy -profile adflibPAASPropertiesModel -workspace PAASPersonProperties.jws -project PAASPropertiesModel -basedir %JDEV_USER_HOME_11119%\mywork\PaaS\PAASProperties -clean > buildjar_PAAS.log 2>&1
%MW_HOME_11119%\jdeveloper\jdev\bin\ojdeploy -profile adflibPAASPropertiesViewController -workspace PAASPersonProperties.jws -project PAASPropertiesViewController -basedir %JDEV_USER_HOME_11119%\mywork\PaaS\PAASProperties -clean > buildjar_PAAS.log 2>&1