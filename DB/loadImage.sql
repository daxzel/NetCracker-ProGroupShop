 create or replace
  procedure loadImage(id_img in number, id_product in number, p_file_name in varchar2, p_dir_name in varchar2,  width in number, height in number)
  as
       l_blob    blob;
       l_bfile   bfile;
   begin
        insert into IMAGE values ( id_img, id_product, p_file_name, empty_blob(), width, height  )returning IMAGE into l_Blob;
        l_bfile := bfilename( p_dir_name, p_file_name );
       dbms_lob.fileopen( l_bfile );
       dbms_lob.loadfromfile( l_blob, l_bfile,dbms_lob.getlength( l_bfile ) );
       dbms_lob.fileclose( l_bfile );
   end;
/
