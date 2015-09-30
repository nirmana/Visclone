
$(function() {
	alert('sss');
	var paths = [ "d1/d2/d3/file152.txt", 
	              "d0/d2/d3/file18.txt",
	              "d1/d2/d0/file1hgg.txt", 
	              "d1/d2/d3/file2.txt",
	              "d2/d3/file.txt",
	              "d3/file4.txt", 
	              "d1/d2/firshht.png", 
	              "d2/secondgg.png",
	              "d1/photo1g.jpg", 
	              "d1/photo2h.jpg",
	              "animate.gif" ];
	//console.log(paths);
	
	var hierarchy = paths.reduce(function(hier,path){
	    var x = hier;
	    path.split('/').forEach(function(item){
	        if(!x[item]){
	            x[item] = {};
	        }
	        x = x[item];
	    });
	    x.path = path;
	    return hier;
	}, {});
	
	var makeul = function(hierarchy, classname){
	    var dirs = Object.keys(hierarchy);
	    var ul = '<ul';
	    if(classname){
	        ul += ' class="' + classname + '"';
	    }
	    ul += '>\n';
	    dirs.forEach(function(dir){
	        var path = hierarchy[dir].path;
	        if(path){ // file
	            ul += '<li class="file" data-url="' + path + '">' + dir + '</li>\n';
	        }else{
	            ul += '<li class="folder"><a>' + dir + '</a>\n';
	            ul += makeul(hierarchy[dir]);
	            ul += '</li>\n';
	        }
	    });
	    ul += '</ul>\n';
	    return ul;
	};

	var html = makeul(hierarchy, 'root');
	console.log('ss');
	//var parent=document.getElementById("fs");
	//parent.appendChild('d');
	document.getElementById("fs").innerHTML='<a href="#"><i class="fa fa-files-o fa-fw"></i> Folder Structure<span class="fa arrow"></span></a>' +html;
});
