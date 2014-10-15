# You must define the global variables $TOPCAT_PATH and $BACKEND_ROOT_PATH

# stop tomcat
cd $TOMCAT_PATH
cd bin
./catalina.sh stop
cd ../webapps

# remove old files
rm -f -r backend/
rm -f backend.war

# make the .war and copy in tomcat
cd $BACKEND_ROOT_PATH
mvn clean install
cd target
mv desapp-groupc-backend-1.0-SNAPSHOT.war backend.war
cp backend.war $TOMCAT_PATH/webapp

# start tomcat
cd $TOMCAT_PATH
cd bin
./catalina.sh start

echo "### DEPLOY FINISH  ###"
