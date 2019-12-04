# -*- mode: ruby -*-
# vi: set ft=ruby :


Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"
  config.ssh.forward_agent = true
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  #ANSIBLE
  config.vm.provision "ansible", run: "always" do |ansible|
	  ansible.playbook = "playbook.yml"
  end

  #EJECUTAR PLAYFY
  config.vm.provision "shell",
    inline: "cd Playfy; ./mvnw spring-boot:start &",
      run: "always"
end
