#!/bin/bash

########################################
# Author: Butch Clark
# Git Project: 
#
# Asks user for input for several
#  different settings.  These are then
#  used to update file/directory names,
#  and also searches all source files
#  for occurrences of tags, and replaces
#  them with the input values.
########################################

source ./term_helper.sh

echo -e "${Clear}"
showTitle
#echo -e "${esc}[48;5;0m"
printf "Enter your ${Orange}git project name ${NC}(i.e. service-somethin-here): "; read gitproject
printf "Enter your ${Orange}User Friendly project name ${NC}(i.e. Serice Something Here): "; read userfriendly
printf "Enter your ${Orange}Pascal-case project name ${NC}(i.e. ServiceSomethingHere): "; read pascalcase
printf "Enter your ${Orange}Java package name ${NC}(i.e. myFeature): "; read pkgname
printf "Enter your ${Orange}Ruby-friendly project name ${NC}(i.e. service_something_here): "; read rubyfriendly
printf "Enter your ${Orange}system env friendly name ${NC}(i.e. SERVICE_SOMETHING_HERE): "; read uppercased
printf "Enter your ${Orange}port number ${NC}(i.e. 8033): "; read portnumber
printf "Enter your ${Orange}simulator port number ${NC}(i.e. 9033): "; read simulatorport
printf "Enter your ${Orange}REST Endpoint ${NC}(i.e. lookupCommitment): "; read restendpoint
printf "Enter your ${Orange}REST Success Msg ${NC}(i.e. Lookup of customer commitments Successful): "; read restsuccess
printf "Enter your ${Orange}REST Endpoint suffix${NC}(i.e. lookupCommitment): "; read restsuffix

echo -e "---------------------------------------"
echo -e " Using the following values:"
echo -e " ${Orange}Git Project name  ${NC}:  ${Yellow}$gitproject${NC}"
echo -e " ${Orange}User Friendly name${NC}:  ${Yellow}$userfriendly${NC}"
echo -e " ${Orange}Pascal-case name  ${NC}:  ${Yellow}$pascalcase${NC}"
echo -e " ${Orange}Java package name ${NC}:  ${Yellow}$pkgname${NC}"
echo -e " ${Orange}Ruby Friendly name${NC}:  ${Yellow}$rubyfriendly${NC}"
echo -e " ${Orange}System Env name   ${NC}:  ${Yellow}$uppercased${NC}"
echo -e " ${Orange}Port Number       ${NC}:  ${Yellow}$portnumber${NC}"
echo -e " ${Orange}Simulator Port    ${NC}:  ${Yellow}$simulatorport${NC}"
echo -e " ${Orange}REST Endpoint     ${NC}:  ${Yellow}$restendpoint${NC}"
echo -e " ${Orange}REST Success Msg  ${NC}:  ${Yellow}$restsuccess${NC}"
echo -e " ${Orange}REST Endpt Suffix ${NC}:  ${Yellow}$restsuffix${NC}"
echo -e "---------------------------------------"

read -p "Continue? (y/n): " contin
uc="$(echo -e "$contin" | tr '[:lower:]' '[:upper:]')"
if [[ "${uc:0:1}" == "N" ]];
then 
    echo -e "Aborting config..."
    exit 0
fi

echo -e "Well, okay, let's do it..."
mkdir ../${gitproject}
cp -r . ../${gitproject}

pushd ../${gitproject}/
rm -rf ./.git
rm -rf ./.idea
echo -e "${Green}Removed git and idea Directories.${NC}"
popd

pushd ../${gitproject}/src/main/java/com/dish/ofm/service/jwa/config
mv CodeChallenge051017Config.java ${pascalcase}Config.java
popd

pushd ../${gitproject}/src/main/java/com/dish/ofm/service
mv CodeChallenge051017Application.java ${pascalcase}Application.java
popd

pushd ../${gitproject}/src/main/java/com/dish/ofm/service/jwa/controller
mv CodeChallenge051017Controller.java ${pascalcase}Controller.java
popd
echo -e "${Green}Renamed java files...${NC}"

pushd ../${gitproject}/src/main/java/com/dish/ofm/service
mv jwa ${pkgname}
popd
echo -e "${Green}Renamed source Directory to ${pkgname}...${NC}"

pushd ../${gitproject}/src/test/java/com/dish/ofm/service/jwa/controller
mv CodeChallenge051017ControllerTest.java ${pascalcase}ControllerTest.java
popd
echo -e "${Green}Renamed java test files...${NC}"

pushd ../${gitproject}/src/test/java/com/dish/ofm/service
mv jwa ${pkgname}
popd
echo -e "${Green}Renamed source Directory to ${pkgname}...${NC}"

pushd ../${gitproject}/acceptance/spec/helpers
mv code_challenge_051017_server.rb ${rubyfriendly}_server.rb
echo -e "${Green}Renamed ruby test files...${NC}"
popd
pushd ../${gitproject}/

for file in $(find . -type f)
do
    sed -i.sedTmp \
    -e s/CodeChallenge051017/${gitproject}/g \
    -e s/Code Challenge 051017/"${userfriendly}"/g \
    -e s/9002/"${simulatorport}"/g \
    -e s/9001/"${portnumber}"/g \
    -e s/fundSummer/"${restendpoint}"/g \
    -e s/Groovy/"${restsuccess}"/g \
    -e s/findSummer/"${restsuffix}"/g \
    -e s/CodeChallenge051017/"${pascalcase}"/g \
    -e s/jwa/"${pkgname}"/g \
    -e s/CODE_CHALLENGE_051917/"${uppercased}"/g \
    -e s/code_challenge_051017/"${rubyfriendly}"/g \
    $file
done
find . -name '*.sedTmp' | xargs rm
echo -e "${Green}Replaced tags in all source files...${NC}" 
echo -e "Updates complete.  "

echo -e "${LBlue}running gradle clean build...${NC}"
gradle clean build
echo -e "${Green}gradle complete. ${NC} "
thumbsUp

cd ../${gitproject}
git init
git add .
git commit -m "initial checkin"
git remote add origin https://gitlab.global.dish.com/pivotal-ofm/${gitproject}.git
git remote -v
git push origin master
echo -e "${Green}git checkin complete. ${NC} "

echo -e "Exiting..."
open -a /Applications/IntelliJ\ IDEA.app .
