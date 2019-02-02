#include "stdafx.h"
#include "EncodingImage.h"


EncodingImage::EncodingImage()
{
}


EncodingImage::~EncodingImage()
{
}

void EncodingImage::getFile(std::string fileName)
{
	this->pFile = fopen(fileName.c_str(), "r");
}
