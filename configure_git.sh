#!/bin/bash

echo "1) Personal"
echo "	ceezuns@gmail.com"
echo ""

echo "2) GitHub Provided"
echo "	49824660+ceezuns@users.noreply.github.com"
echo ""

echo "Choose A Profile: "
read profile

if [ $profile == "1" ]; then
	git config user.email "ceezuns@gmail.com"
	git config user.signingkey "2E0F398AFF319A59"
elif [ $profile == "2" ]; then
	git config user.email "49824660+ceezuns@users.noreply.github.com"
	git config user.signingkey "D38E310072F1AC23"
else
	echo "The input was invalid, please relaunch the script!"
fi
