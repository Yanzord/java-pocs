#!/bin/bash -e

# Install Ansible and python repository
apt -y update && apt-get -y upgrade
apt -y install software-properties-common
apt-add-repository ppa:ansible/ansible
apt-add-repository ppa:jonathonf/python-3.6

#Install Ansible and python
apt -y update
apt -y install ansible
apt -y install python3.6