server { 
	listen 80 default; 
	root /var/www/html; 
	location / { 
		try_files /index.html /index.html /index.html =404;
	}     
} 
