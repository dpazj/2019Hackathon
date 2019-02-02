
#include "stdafx.h"
#include "EncodingFile.h"


EncodingFile::EncodingFile(std::string fileName)
{
	this->setPFile(fileName);
	this->initValues();
	this->printRowValues();
}


EncodingFile::~EncodingFile()
{
}

void EncodingFile::setPFile(std::string fileName) 
{
	this->pFile = std::fopen(fileName.c_str(), "r");
}

void EncodingFile::initValues() {
	png_structp pngptr = png_create_read_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
	png_infop pnginfo = png_create_info_struct(pngptr);
	png_init_io(pngptr, this->pFile);
	this->rows = png_get_rows(pngptr, pnginfo);
	this->width = png_get_image_width(pngptr, pnginfo);
	this->height = png_get_image_height(pngptr, pnginfo);
}

void EncodingFile::printRowValues() {
	for (int i = 0; i < this->height; i++) {
		for (int j = 0; j < this->width; j++) {
			printf("%d %d %d ", rows[i][j], rows[i][j + 1], rows[i][j + 2]);
		}
		printf("\n");
	}
}


