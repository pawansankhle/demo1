function hasValue(val)
{
	return (val != null && val != undefined && val != NaN && val != "NaN" && val != "null" && val != "undefined" && (val != "" || String(val) == "0") && val != "-Please select-" && val != "--" && val != "?_s=");
}
