package com.swapi.util

import androidx.annotation.IntDef

/**
 * Prem's creation, on 28/12/20
 *
 * Sort definition for Sorting Star wars data, values can be [ASC] or [DESC]
 */
@Retention @IntDef(ASC, DESC) annotation class SWSort
const val ASC = 0
const val DESC = 1

