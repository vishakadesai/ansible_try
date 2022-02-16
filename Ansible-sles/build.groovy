#!/usr/bin/env groovy
def call() {
def WORK_DIR = pwd();
withEnv(["WORK_DIR=${WORK_DIR}"]) {
withCredentials([string(credentialsId: 'github-token', variable: 'TOKEN')]) {
script {
sh '''
	. /etc/os-release
	echo $VERSION
    # install dependencies
	if [[ $VERSION == "12-SP5" ]]; then
		echo "Inside SLES12-SP5"
		sudo zypper install -y libffi48-devel openssl-devel gcc make gzip
	elif [[ $VERSION == "15-SP2" || $VERSION == "15-SP3" ]]; then
		echo "Inside SLES15x"
        sudo zypper install -y gcc libffi-devel make openssl-devel wget
  	fi

    # install Python
    wget -q https://raw.githubusercontent.com/linux-on-ibm-z/scripts/master/Python3/3.10.2/build_python3.sh

  # build Python
    bash build_python3.sh  

	# install Ansible
    sudo pip3 install cryptography==3.3.2
    sudo pip3 install ansible
 '''
}
}
}
}
return this;
