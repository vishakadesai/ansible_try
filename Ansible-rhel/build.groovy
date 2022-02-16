#!/usr/bin/env groovy
def call() {
def WORK_DIR = pwd();
withEnv(["WORK_DIR=${WORK_DIR}"]) {
withCredentials([string(credentialsId: 'github-token', variable: 'TOKEN')]) {
script {
sh '''
	source /home/test/.bashrc
   	test=$( cat /etc/redhat-release | grep release)
	echo $test
	
	if [[ $test == *"Red Hat Enterprise Linux Server release 7."* ]]; then
	    echo "Inside RHEL7"
	    # Install Dependencies
		sudo yum install -y gcc libffi-devel openssl-devel
		
	elif [[ $test == *"Red Hat Enterprise Linux release 8."* ]]; then
     		echo "Inside RHEL8"
	    	# Install Dependencies
        	sudo yum install -y python39-devel gcc libffi-devel openssl-devel
        fi
	
	# Install Python
		wget -q https://raw.githubusercontent.com/linux-on-ibm-z/scripts/master/Python3/3.10.2/build_python3.sh

    # Build Python
        bash build_python3.sh  
	
	# Install Ansible
        sudo pip3 install cryptography==3.3.2
        sudo pip3 install ansible
 '''
}
}
}
}
return this;
