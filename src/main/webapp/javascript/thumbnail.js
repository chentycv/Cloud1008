semantic.thumbnail = {};

// ready event
semantic.thumbnail.ready = function(){
    
    // icons cache
	var
        icons = {
            folder: "./images/icons/default-folder.svg",
            doc: "./images/icons/application-word.svg",
            xls: "./images/icons/application-table.svg",
            ppt: "./images/icons/application-presentation.svg",
            video: "./images/icons/application-video.svg",
            audio: "./images/icons/application-audio.svg",
            archive: "./images/icons/application-archive.svg",            
            file: "./images/icons/application-document.svg"      
        },

        // alias
        handler
	;
	
    // event handlers
    semantic.thumbnail.handler = {
        
        getFileExtension : function(name) {
            return (/[.]/.exec(name)) ? (/[^.]+$/.exec(name))[0] : undefined;
        },
        
        getThumbnail : function(file){
        
            if (file.type === "folder") {
                return icons.folder;                
            } else { 
                var ext = semantic.thumbnail.handler.getFileExtension(file.name);
                switch (ext){
                    case "doc":
                    case "docx": return icons.doc; break;
                    case "xls":
                    case "xlsx": return icons.xls; break;
                    case "ppt":
                    case "pptx": return icons.ppt; break;
                    case "mp4": 
                    case "avi": 
                    case "flv":
                    case "rmvb":
                    case "mpg": return icons.video; break;
                    case "zip": 
                    case "rar": 
                    case "7z": 
                    case "cab":
                    case "tar":
                    case "gz": return icons.archive; break;
                    case "mp3": 
                    case "wav": return icons.audio; break;
                    default : return icons.file;
                }
            }
        
        }
        
    }
    ;
        
}
;

// attach ready event
$(document)
    .ready(semantic.thumbnail.ready)
;