#!/usr/bin/env groovy
def call() {
def WORK_DIR = pwd();
withEnv(["WORK_DIR=${WORK_DIR}"]) {
script {
sh '''
	. /etc/os-release
	echo $VERSION_ID
	# Install Dependencies
	if [[ $VERSION == "18.04" ]]; then
		sudo DEBIAN_FRONTEND=noninteractive apt-get update -y && sudo DEBIAN_FRONTEND=noninteractive apt-get install -y python3.8-dev python3.8 python3-markupsafe python3-pip gcc libffi-dev libssl-dev && sudo ln -s /usr/bin/python3.8 /usr/bin/python
	fi

	# Install Ansible
	sudo pip3 install cryptography==3.3.2
    sudo python -m pip install ansible   

'''
}
}
}
return this;
