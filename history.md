# History of used commands 
## 1
mkdir petshop    
cd petshop/    
cat > domestic_animals    
cat > herd_animals    
cat domestic_animals herd_animals > animals    
cat animals    
mv animals humanity_friends    
ls   

## 2
cd
mkdir petshop_system    
cd ~/petshop    
mv humanity_friends ~/petshop_system    
cd ~/petshop_system    
ls

## 3
sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.23-1_all.deb    
sudo dpkg -i mysql-apt-config_0.8.23-1_all.deb    
sudo apt-get install mysql-server    

## 4
sudo wget https://download.docker.com/linux/ubuntu/dists/jammy/pool/stable/amd64/docker-ce-cli_20.10.13~3-0~ubuntu-jammy_amd64.deb    
clear
sudo dpkg -i docker-ce-cli_20.10.13~3-0~ubuntu-jammy_amd64.deb    
clear
sudo dpkg -r --force-depends docker-ce-cli


