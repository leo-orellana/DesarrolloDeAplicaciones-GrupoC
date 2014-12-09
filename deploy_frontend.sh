# You must define the global variables $SECOND_TOPCAT_PATH and $FRONTEND_ROOT_PATH

# stop tomcat
cd $TOMCAT_PATH
cd bin
./catalina.sh stop
cd ../webapps

# remove old files
rm -f -r frontend/
rm -f sifeag.war

# make the .war and copy in tomcat
cd $FRONTEND_ROOT_PATH
mvn clean install
cd target
mv desapp-groupc-frontend.war sifeag.war
cp sifeag.war $TOMCAT_PATH/webapps

# start tomcat
cd $TOMCAT_PATH
cd bin
./catalina.sh start

echo "### DEPLOY FINISH  ###"
