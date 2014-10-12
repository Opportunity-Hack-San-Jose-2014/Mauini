/**
 * New node file
 */
var datapool = require('./database');
var multipart = require('multipart');
var fs = require('fs');
var formidable = require('formidable');


var test = function(req , res){
	var conn = getconnection(req , res);
}

exports.test = test;
var getconnection = function(req,res){
	
	var pool = datapool.getPoolInstance();
	return pool;
/*	pool.getConnection(function(err , connection){
		if(!err){
			console.log("yosshhaaaa");
		}else{
			console.log("locho padyo");
		}
		
	})
*/};

exports.uploadProduct1 = function(req,res)
{
	console.log(req.body);
}

exports.uploadProduct=function(req,res){
	var postAuthorId=3//req.param('postId');
	var post_content='hello'//req.param('post_content');
	var post_title = req.param('post_title');//var post_title='t1'
	var post_excerpt = req.param('post_excerpt')//var post_excerpt='hgv';
	var regular_price = req.param('regular_price');//var regular_price=55;
	var sale_price = req.param('sale_price')//var sale_price=45;
	var weight=2//req.param('weight');
	var height=1//req.param('height');
	var length=2//req.param('length');
	var sku=6//req.param('sku');
	var sale_date='';
	var guid='';
	var img=req.param('');
	var fs=require('fs');
	
	if(postAuthorId==null){
		res.send('error');
	}
	if(post_content==null){
		post_content='';
	}
	if(post_title==null){
		res.send('error');
	}
	if(post_excerpt==null){
		post_excerpt='';
	}
	if(regular_price==null){
		res.send('error-no regular price');
	}
	if(sale_price==null){
		sale_price='';
	}
	if(weight==null){
		weight='';
	}
	if(length==null){
		length='';
	}
	if(sku==null){
		sku='';
	}
	if(sale_date==null){
		sale_date='';
	}
	
	var form = new formidable.IncomingForm();
	
	/*form.parse(req, function(err, fields) {
        if (err) {

          // Check for and handle any errors here.

          console.error(err.message);
          
        }
        else{
        	console.log(fields);
        }

        // This last line responds to the form submission with a list of the parsed data and files.

        res.end(util.inspect({fields: fields, files: files}));
      });*/
     
	/*multipart.parse(req).addCallback(function(parts){
		console.log(parts);
	});*/
	
	
	fs.writefile("filepath","filedata",function(err){
		if(err)console.log("error in saving image : " + err);
	});
	
	var query="insert into wp_posts (post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count) values ("+postAuthorId+",now(),NOW(),'"+post_content+"','"+post_title+"','"+post_excerpt+"','publish','open','closed','','"+post_title+"','','',NOW(),NOW(),'',0,'"+guid+"',0,'product','',0)";
	
			console.log(query);
	var conn=getconnection(req,res);
	conn.query(query,function(err,rows){
		if(!err){
			console.log('done!');
		}
		else{
			console.log(err);
		}
	});
}; 

exports.getconnection = getconnection;